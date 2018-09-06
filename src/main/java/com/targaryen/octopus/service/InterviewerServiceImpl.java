package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.InterviewerStatus;
import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    }

    public List<InterviewVo> listInterviewsByInterviewerId(int interviewerId) {
        InterviewerDto interviewerDto;
        List<InterviewDto> interviewDtos;
        List<InterviewVo> interviewVos = new ArrayList<>();

        try {
            interviewerDto = interviewerDtoRepository.findInterviewerDtoByInterviewerId(interviewerId);
            if(interviewerDto == null)
                return new ArrayList<>();
            interviewDtos = interviewerDto.getInterviews();

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

//    public int setInterviewerStatus(int interviewerStatus, int interviewId) {
//        InterviewDto interviewDto;
//
//        try {
//            interviewDto = interviewDtoRepository.findInterviewDtoByInterviewId(interviewId);
//            if(interviewDto == null)
//                return StatusCode.FAILURE;
//            interviewDto.setInterviewerStatus(interviewerStatus);
//            if(InterviewerStatus.REJECTED.equals(interviewerStatus)) {
//
//            }
//
//        }
//    }

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

        return new ResumeVo.Builder()
                .resumeId(resumeDto.getResumeId())
                .applicantSex(resumeDto.getApplicantSex())
                .applicantSchool(resumeDto.getApplicantSchool())
                .applicantPhone(resumeDto.getApplicantPhone())
                .applicantName(resumeDto.getApplicantName())
                .applicantMajor(resumeDto.getApplicantMajor())
                .applicantEmail(resumeDto.getApplicantEmail())
                .applicantDegree(resumeDto.getApplicantDegree())
                .applicantCV(resumeDto.getApplicantCV())
                .applicantCity(resumeDto.getApplicantCity())
                .applicantAge(resumeDto.getApplicantAge()).build();
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
