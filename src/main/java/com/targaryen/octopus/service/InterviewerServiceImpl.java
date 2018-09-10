package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.InterviewResultStatus;
import com.targaryen.octopus.util.status.InterviewerStatus;
import com.targaryen.octopus.util.status.ReservationStatus;
import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.InterviewerInterviewVo;
import com.targaryen.octopus.vo.InterviewerVo;
import com.targaryen.octopus.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Liu Mengyang on 2018/09/05
 */
@Service
public class InterviewerServiceImpl implements InterviewerService {
    private InterviewerDtoRepository interviewerDtoRepository;
    private ResumeDtoRepository resumeDtoRepository;
    private UserDtoRepository userDtoRepository;
    private ApplicationDtoRepository applicationDtoRepository;
    private InterviewDtoRepository interviewDtoRepository;

    @Autowired
    public InterviewerServiceImpl(DaoFactory daoFactory) {
        this.interviewerDtoRepository = daoFactory.getInterviewerDtoRepository();
        this.resumeDtoRepository = daoFactory.getResumeDtoRepository();
        this.userDtoRepository = daoFactory.getUserDtoRepository();
        this.applicationDtoRepository = daoFactory.getApplicationDtoRepository();
        this.interviewDtoRepository = daoFactory.getInterviewDtoRepository();
    }

    private List<InterviewDto> listInterviewDtosByInterviewerId(int interviewerId) {
        InterviewerDto interviewerDto;
        List<InterviewDto> interviewDtos;

        try {
            interviewerDto = interviewerDtoRepository.findInterviewerDtoByInterviewerId(interviewerId);
            if(interviewerDto == null)
                return new ArrayList<>();
            interviewDtos = interviewerDto.getInterviews();

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return interviewDtos;
    }

    public List<InterviewerInterviewVo> listInterviewerInterviewsByInterviewerId(int interviewerId) {
        List<InterviewDto> interviewDtos;
        List<InterviewerInterviewVo> interviewVos = new ArrayList<>();

        try {

            interviewDtos = listInterviewDtosByInterviewerId(interviewerId);

            for(InterviewDto interviewDto: interviewDtos) {
                interviewVos.add(
                        DataTransferUtil.InterviewDtoToInterviewerInterviewVo(interviewDto)
                );
            }

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return interviewVos;
    }

    public List<InterviewVo> listInterviewsByInterviewerId(int interviewerId) {
        List<InterviewDto> interviewDtos;
        List<InterviewVo> interviewVos = new ArrayList<>();

        try {

            interviewDtos = listInterviewDtosByInterviewerId(interviewerId);

            for(InterviewDto interviewDto: interviewDtos) {
                interviewVos.add(
                        DataTransferUtil.InterviewDtoToVo(interviewDto)
                );
            }

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return interviewVos;
    }

    public List<InterviewVo> listUnreplyedInterviewsByInterviewerId(int interviewerId) {
        List<InterviewVo> interviewVos = listInterviewsByInterviewerId(interviewerId);
        return interviewVos.stream().filter(x ->
                (x.getReservationStatus() == ReservationStatus.INIT) &&
                        (x.getInterviewerStatus() == InterviewerStatus.INIT))
                .collect(Collectors.toList());
    }

    public List<InterviewVo> listActiveInterviewsByInterviewerId(int interviewerId) {
        List<InterviewVo> interviewVos = listInterviewsByInterviewerId(interviewerId);
        return interviewVos.stream().filter(x ->
                (x.getReservationStatus() == ReservationStatus.SUCCESS) &&
                        (x.getInterviewResultStatus() == InterviewResultStatus.INIT))
                .collect(Collectors.toList());
    }


    public int setInterviewerStatus(int interviewerStatus, int interviewId, String comment) {
        InterviewDto interviewDto;

        try {
            interviewDto = interviewDtoRepository.findInterviewDtoByInterviewId(interviewId);
            if(interviewDto == null)
                return StatusCode.FAILURE;
            interviewDto.setInterviewerStatus(interviewerStatus);
            if(InterviewerStatus.REJECTED.equals(interviewerStatus)) {
                interviewDto.setReservationResultTime(Calendar.getInstance().getTime());
                interviewDto.setInterviewerComment(comment);
                interviewDto.setReservationStatus(ReservationStatus.FAIL);
            }
            interviewDtoRepository.save(interviewDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public int setInterviewResultComment(int interviewId, String comment) {
        InterviewDto interviewDto;

        try {
            interviewDto = interviewDtoRepository.findInterviewDtoByInterviewId(interviewId);
            if(interviewDto == null)
                return StatusCode.FAILURE;
            interviewDto.setInterviewResultComment(comment);
            interviewDtoRepository.save(interviewDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public ResumeVo findResumeByApplicationId(int applicationId) {
        ApplicationDto applicationDto;
        ApplicantDto applicantDto;
        ResumeDto resumeDto;
        try {
            applicationDto = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            if(applicationDto == null)
                return null;
            applicantDto = applicationDto.getApplicant();
            if(applicantDto == null)
                return null;
            resumeDto = applicantDto.getResume();
            if(resumeDto == null)
                return null;
        } catch (DataAccessException e) {
            return null;
        }

        return DataTransferUtil.ResumeDtoToVo(resumeDto);
    }

    public ResumeVo findResumeByInterviewId(int interviewId) {
        InterviewDto interviewDto;
        ApplicationDto applicationDto;

        try {
            interviewDto = interviewDtoRepository.findInterviewDtoByInterviewId(interviewId);
            if(interviewDto == null)
                return null;
            applicationDto = interviewDto.getApplication();
            if(applicationDto == null)
                return null;
        } catch (DataAccessException e) {
            return null;
        }

        return findResumeByApplicationId(applicationDto.getApplicationId());

    }
}
