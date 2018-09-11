package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.ApplicantStatus;
import com.targaryen.octopus.util.status.ApplicationStatus;
import com.targaryen.octopus.util.status.InterviewerStatus;
import com.targaryen.octopus.util.status.ReservationStatus;
import com.targaryen.octopus.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Created by Liu Mengyang on 2018/09/05
 */
@Service
public class ApplicantServiceImpl implements ApplicantService {

    private ApplicantDtoRepository applicantDtoRepository;
    private UserDtoRepository userDtoRepository;
    private ResumeDtoRepository resumeDtoRepository;
    private PostDtoRepository postDtoRepository;
    private ApplicationDtoRepository applicationDtoRepository;
    private InterviewDtoRepository interviewDtoRepository;
    private ResumeModelDtoRepository resumeModelDtoRepository;

    @Autowired
    public ApplicantServiceImpl(DaoFactory daoFactory) {
        this.applicantDtoRepository = daoFactory.getApplicantDtoRepository();
        this.userDtoRepository = daoFactory.getUserDtoRepository();
        this.resumeDtoRepository = daoFactory.getResumeDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.applicantDtoRepository = daoFactory.getApplicantDtoRepository();
        this.interviewDtoRepository = daoFactory.getInterviewDtoRepository();
        this.applicationDtoRepository = daoFactory.getApplicationDtoRepository();
        this.resumeModelDtoRepository = daoFactory.getResumeModelDtoRepository();
    }


    public int SaveResume(int userId, ResumeVo resumeVo) {
        ResumeDto resumeDto;
        ApplicantDto applicantDto;

        applicantDto = applicantDtoRepository.findApplicantDtoByApplicantId(resumeVo.getApplicantId());

        if(findResumeByUserId(userId) == null) {
            resumeDto = new ResumeDto();
            resumeDto.setApplicant(applicantDto);
        }
        else {
            resumeDto = applicantDto.getResume();
        }

        try {
            applicantDto.setResume(resumeDto);
            resumeDto.setApplicantAge(resumeVo.getApplicantAge());
            resumeDto.setApplicantCity(resumeVo.getApplicantCity());
            resumeDto.setApplicantCV(resumeVo.getApplicantCV());
            resumeDto.setApplicantDegree(resumeVo.getApplicantDegree());
            resumeDto.setApplicantEmail(resumeVo.getApplicantEmail());
            resumeDto.setApplicantMajor(resumeVo.getApplicantMajor());
            resumeDto.setApplicantName(resumeVo.getApplicantName());
            resumeDto.setApplicantPhone(resumeVo.getApplicantPhone());
            resumeDto.setApplicantSchool(resumeVo.getApplicantSchool());
            resumeDto.setApplicantSex(resumeVo.getApplicantSex());
            resumeDtoRepository.save(resumeDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public ResumeVo findResumeByUserId(int userId) {
        ApplicantDto applicantDto;
        UserDto userDto;
        ResumeDto resumeDto;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            applicantDto = userDto.getApplicant();
            if(applicantDto == null)
                return null;
            resumeDto = applicantDto.getResume();
            if(resumeDto == null)
                return null;
        } catch (DataAccessException e) {
            return null;
        }

        return new ResumeVo.Builder()
                .applicantAge(resumeDto.getApplicantAge())
                .applicantCity(resumeDto.getApplicantCity())
                .applicantCV(resumeDto.getApplicantCV())
                .applicantDegree(resumeDto.getApplicantDegree())
                .applicantEmail(resumeDto.getApplicantEmail())
                .applicantMajor(resumeDto.getApplicantMajor())
                .applicantName(resumeDto.getApplicantName())
                .applicantPhone(resumeDto.getApplicantPhone())
                .applicantSchool(resumeDto.getApplicantSchool())
                .applicantSex(resumeDto.getApplicantSex())
                .resumeId(resumeDto.getResumeId()).build();
    }


    public int CreateNewApplication(ApplicationVo applicationVo) {
        ApplicationDto applicationDto = new ApplicationDto();
        PostDto postDto;
        ApplicantDto applicantDto;
        List<ApplicationDto> existed = applicationDtoRepository.findByApplicantAndPost(
                applicantDtoRepository.findApplicantDtoByApplicantId(applicationVo.getApplicantId()),
                postDtoRepository.findPostDtoByPostId(applicationVo.getPostId())
        );


        for(ApplicationDto a: existed) {
            if(a.getStatus() >= 0)
                return StatusCode.FAILURE;
        }


        applicationDto.setStatus(ApplicationStatus.INIT);

        try {
            postDto = postDtoRepository.findPostDtoByPostId(applicationVo.getPostId());
            applicantDto = applicantDtoRepository.findApplicantDtoByApplicantId(applicationVo.getApplicantId());
            applicationDto.setApplicant(applicantDto);
            applicationDto.setPost(postDto);
            applicationDto.setCreateTime(Calendar.getInstance().getTime());
            applicationDtoRepository.save(applicationDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public List<ApplicantApplicationVo> findApplicationsByUserId(int userId) {
        UserDto userDto;
        ApplicantDto applicantDto;
        List<ApplicationDto> applicationDtos;
        List<ApplicantApplicationVo> applicationVos = new ArrayList<>();

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return new ArrayList<>();
            applicantDto = userDto.getApplicant();
            if(applicantDto == null)
                return new ArrayList<>();
            applicationDtos = applicantDto.getApplications();
            for(ApplicationDto a: applicationDtos) {
                applicationVos.add(
                        DataTransferUtil.ApplicationDtoToApplicantApplicationVo(a)
                );
            }
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return applicationVos;
    }

    private List<InterviewDto> findInterviewDtosByUserIdAndApplicantStatus(int userId, int applicantStatus) {
        UserDto userDto;
        ApplicantDto applicantDto;
        List<ApplicationDto> applicationDtos;
        List<InterviewDto> interviewDtos = new ArrayList<>();
        List<InterviewDto> filtered;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return new ArrayList<>();
            applicantDto = userDto.getApplicant();
            if(applicantDto == null)
                return new ArrayList<>();
            applicationDtos = applicantDto.getApplications();

        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        for(ApplicationDto a: applicationDtos) {
            interviewDtos.addAll(a.getInterviews());
        }

        filtered = interviewDtos.stream()
                .filter(x -> (x.getApplicantStatus() == applicantStatus)
                        && (x.getInterviewerStatus() == InterviewerStatus.ACCEPTED))
                .collect(Collectors.toList());

        return filtered;
    }

    private List<InterviewVo> findInterviewsByUserIdAndApplicantStatus(int userId, int applicantStatus) {
        List<InterviewDto> filtered = findInterviewDtosByUserIdAndApplicantStatus(userId, applicantStatus);
        List<InterviewVo> interviewVos = new ArrayList<>();

        for(InterviewDto i: filtered) {
            interviewVos.add(DataTransferUtil.InterviewDtoToVo(i));
        }

        return interviewVos;
    }

    public List<InterviewVo> findUnreplyedInterviewsByUserId(int userId) {
        return findInterviewsByUserIdAndApplicantStatus(userId, ApplicantStatus.INIT);
    }

    public List<InterviewVo> findAcceptedInterviewsByUserId(int userId) {
        return findInterviewsByUserIdAndApplicantStatus(userId, ApplicantStatus.ACCEPTED);
    }

    private List<ApplicantInterviewVo> findInterviewDetailsByUserIdAndApplicantStatus(int userId, int applicantStatus) {
        List<InterviewDto> filtered = findInterviewDtosByUserIdAndApplicantStatus(userId, applicantStatus);
        List<ApplicantInterviewVo> interviewVos = new ArrayList<>();

        for(InterviewDto i: filtered) {
            interviewVos.add(DataTransferUtil.InterviewDtoToApplicantInterviewVo(i));
        }

        return interviewVos;
    }

    public List<ApplicantInterviewVo> findUnreplyedInterviewDetailsByUserId(int userId) {
        return findInterviewDetailsByUserIdAndApplicantStatus(userId, ApplicantStatus.INIT);
    }

    public List<ApplicantInterviewVo> findAcceptedInterviewDetailsByUserId(int userId) {
        return findInterviewDetailsByUserIdAndApplicantStatus(userId, ApplicantStatus.ACCEPTED);
    }



    public int updateApplicantStatusOfInterview(int interviewId, int applicantStatus, String comment) {
        InterviewDto interviewDto;

        try {
            interviewDto = interviewDtoRepository
                    .findInterviewDtoByInterviewId(interviewId);
            interviewDto.setApplicantStatus(applicantStatus);
            if(ApplicantStatus.REJECTED.equals(applicantStatus)) {
                interviewDto.setReservationResultTime(Calendar.getInstance().getTime());
                interviewDto.setReservationStatus(ReservationStatus.FAIL);
                interviewDto.setApplicantComment(comment);
            } else if (ApplicantStatus.ACCEPTED.equals(applicantStatus)){
                interviewDto.setReservationResultTime(Calendar.getInstance().getTime());
                interviewDto.setReservationStatus(ReservationStatus.SUCCESS);
            }
            interviewDtoRepository.save(interviewDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public ResumeModelVo findResumeModelByPostId(int postId) {
        PostDto postDto;
        ResumeModelDto resumeModelDto;

        try {
            postDto = postDtoRepository.findPostDtoByPostId(postId);
            if(postDto == null)
                return null;
            resumeModelDto = postDto.getResumeModel();
            if(resumeModelDto == null)
                return null;
        } catch (DataAccessException e) {
            return null;
        }

        return DataTransferUtil.ResumeModeDtoToVo(resumeModelDto);
    }

    public int saveResumeWithModel(ResumeVo resumeVo, int modelId) {
        ResumeDto resumeDto;
        ResumeModelDto resumeModelDto;
        ApplicantDto applicantDto;
        int applicantId = resumeVo.getApplicantId();

        try {
            applicantDto = applicantDtoRepository.findApplicantDtoByApplicantId(applicantId);
            resumeDto = applicantDto.getResume();
            if(resumeDto == null) {
                resumeDto = new ResumeDto();
                resumeDto.setApplicantName(resumeVo.getApplicantName());
                resumeDto.setApplicant(applicantDto);
                resumeDtoRepository.save(resumeDto);
                applicantDto = applicantDtoRepository.findApplicantDtoByApplicantId(applicantId);
                resumeDto = applicantDto.getResume();
            }

            resumeModelDto = resumeModelDtoRepository.findResumeModelDtoByResumeModelId(modelId);

            if(resumeModelDto.isApplicantName())
                resumeDto.setApplicantName(resumeVo.getApplicantName());
            if(resumeModelDto.isApplicantSex())
                resumeDto.setApplicantSex(resumeVo.getApplicantSex());
            if(resumeModelDto.isApplicantAge())
                resumeDto.setApplicantAge(resumeVo.getApplicantAge());
            if(resumeModelDto.isApplicantSchool())
                resumeDto.setApplicantSchool(resumeVo.getApplicantSchool());
            if(resumeModelDto.isApplicantDegree())
                resumeDto.setApplicantDegree(resumeVo.getApplicantDegree());
            if(resumeModelDto.isApplicantMajor())
                resumeDto.setApplicantMajor(resumeVo.getApplicantMajor());
            if(resumeModelDto.isApplicantCity())
                resumeDto.setApplicantCity(resumeVo.getApplicantCity());
            if(resumeModelDto.isApplicantEmail())
                resumeDto.setApplicantEmail(resumeVo.getApplicantEmail());
            if(resumeModelDto.isApplicantPhone())
                resumeDto.setApplicantPhone(resumeVo.getApplicantPhone());
            if(resumeModelDto.isApplicantCV())
                resumeDto.setApplicantCV(resumeVo.getApplicantCV());
            if(resumeModelDto.isApplicantHometown())
                resumeDto.setApplicantHometown(resumeVo.getApplicantHometown());
            if(resumeModelDto.isApplicantNation())
                resumeDto.setApplicantNation(resumeVo.getApplicantNation());
            if(resumeModelDto.isApplicantPoliticalStatus())
                resumeDto.setApplicantPoliticalStatus(resumeVo.getApplicantPoliticalStatus());
            if(resumeModelDto.isApplicantMaritalStatus())
                resumeDto.setApplicantMaritalStatus(resumeVo.getApplicantMaritalStatus());
            if(resumeModelDto.isApplicantDateOfBirth())
                resumeDto.setApplicantDateOfBirth(resumeVo.getApplicantDateOfBirth());
            if(resumeModelDto.isApplicantTimeToWork())
                resumeDto.setApplicantTimeToWork(resumeVo.getApplicantTimeToWork());
            if(resumeModelDto.isApplicantCurrentSalary())
                resumeDto.setApplicantCurrentSalary(resumeVo.getApplicantCurrentSalary());
            if(resumeModelDto.isApplicantExpectSalary())
                resumeDto.setApplicantExpectSalary(resumeVo.getApplicantExpectSalary());
            if(resumeModelDto.isApplicantDutyTime())
                resumeDto.setApplicantDutyTime(resumeVo.getApplicantDutyTime());
            if(resumeModelDto.isRecommenderName())
                resumeDto.setRecommenderName(resumeVo.getRecommenderName());
            if(resumeModelDto.isApplicantAddress())
                resumeDto.setApplicantAddress(resumeVo.getApplicantAddress());
            if(resumeModelDto.isApplicantSelfIntro())
                resumeDto.setApplicantSelfIntro(resumeVo.getApplicantSelfIntro());
            if(resumeModelDto.isApplicantPhoto())
                resumeDto.setApplicantPhoto(resumeVo.getApplicantPhoto());
            if(resumeModelDto.isApplicantDegreePhoto())
                resumeDto.setApplicantDegreePhoto(resumeVo.getApplicantDegreePhoto());
            if(resumeModelDto.isFamilyContactRelation())
                resumeDto.setFamilyContactRelation(resumeVo.getFamilyContactRelation());
            if(resumeModelDto.isFamilyContactName())
                resumeDto.setFamilyContactName(resumeVo.getFamilyContactName());
            if(resumeModelDto.isFamilyContactCompany())
                resumeDto.setFamilyContactCompany(resumeVo.getFamilyContactCompany());
            if(resumeModelDto.isFamilyContactPhoneNum())
                resumeDto.setFamilyContactPhoneNum(resumeVo.getFamilyContactPhoneNum());

        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

}
