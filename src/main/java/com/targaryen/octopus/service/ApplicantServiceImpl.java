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
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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
    private WorkExperienceRepository workExperienceRepository;
    private EducationExperienceRepository educationExperienceRepository;

    @PersistenceContext
    EntityManager entityManager;

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
        this.workExperienceRepository = daoFactory.getWorkExperienceRepository();
        this.educationExperienceRepository = daoFactory.getEducationExperienceRepository();
    }


    public int saveResume(ResumeVo resumeVo) {
        ResumeDto resumeDto;
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

            resumeDto.setApplicantName(resumeVo.getApplicantName());
            resumeDto.setApplicantSex(resumeVo.getApplicantSex());
            resumeDto.setApplicantAge(resumeVo.getApplicantAge());
            resumeDto.setApplicantSchool(resumeVo.getApplicantSchool());
            resumeDto.setApplicantDegree(resumeVo.getApplicantDegree());
            resumeDto.setApplicantMajor(resumeVo.getApplicantMajor());
            resumeDto.setApplicantCity(resumeVo.getApplicantCity());
            resumeDto.setApplicantEmail(resumeVo.getApplicantEmail());
            resumeDto.setApplicantPhone(resumeVo.getApplicantPhone());
            resumeDto.setApplicantCV(resumeVo.getApplicantCV());
            resumeDto.setApplicantHometown(resumeVo.getApplicantHometown());
            resumeDto.setApplicantNation(resumeVo.getApplicantNation());
            resumeDto.setApplicantPoliticalStatus(resumeVo.getApplicantPoliticalStatus());
            resumeDto.setApplicantMaritalStatus(resumeVo.getApplicantMaritalStatus());
            resumeDto.setApplicantDateOfBirth(resumeVo.getApplicantDateOfBirth());
            resumeDto.setApplicantTimeToWork(resumeVo.getApplicantTimeToWork());
            resumeDto.setApplicantCurrentSalary(resumeVo.getApplicantCurrentSalary());
            resumeDto.setApplicantExpectSalary(resumeVo.getApplicantExpectSalary());
            resumeDto.setApplicantDutyTime(resumeVo.getApplicantDutyTime());
            resumeDto.setRecommenderName(resumeVo.getRecommenderName());
            resumeDto.setApplicantAddress(resumeVo.getApplicantAddress());
            resumeDto.setApplicantSelfIntro(resumeVo.getApplicantSelfIntro());
            resumeDto.setApplicantPhoto(resumeVo.getApplicantPhoto());
            resumeDto.setApplicantDegreePhoto(resumeVo.getApplicantDegreePhoto());
            resumeDto.setFamilyContactRelation(resumeVo.getFamilyContactRelation());
            resumeDto.setFamilyContactName(resumeVo.getFamilyContactName());
            resumeDto.setFamilyContactCompany(resumeVo.getFamilyContactCompany());
            resumeDto.setFamilyContactPhoneNum(resumeVo.getFamilyContactPhoneNum());

            resumeDtoRepository.save(resumeDto);

        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return  StatusCode.SUCCESS;
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

    public int createWorkExperience(WorkExperienceVo workExperienceVo) {
        WorkExperienceDto workExperienceDto = new WorkExperienceDto();
        ResumeDto resumeDto;

        try {
            resumeDto = resumeDtoRepository.findResumeDtoByResumeId(workExperienceVo.getResumeId());
            if(resumeDto == null)
                return StatusCode.FAILURE;
            workExperienceDto.setResume(resumeDto);
            workExperienceDto.setAchievement(workExperienceVo.getAchievement());
            workExperienceDto.setCity(workExperienceVo.getCity());
            workExperienceDto.setCompany(workExperienceVo.getCompany());
            workExperienceDto.setStartTime(workExperienceVo.getStartTime());
            workExperienceDto.setEndTime(workExperienceVo.getEndTime());
            workExperienceDto.setPost(workExperienceVo.getPost());
            workExperienceDto.setReferenceName(workExperienceVo.getReferenceName());
            workExperienceDto.setReferencePhoneNum(workExperienceVo.getReferencePhoneNum());
            workExperienceDto.setWorkDiscription(workExperienceVo.getWorkDescription());
            workExperienceRepository.save(workExperienceDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    public int createEducationExperience(EducationExperienceVo educationExperienceVo) {
        EducationExperienceDto educationExperienceDto = new EducationExperienceDto();
        ResumeDto resumeDto;

        try {
            resumeDto = resumeDtoRepository.findResumeDtoByResumeId(educationExperienceVo.getResumeId());
            if(resumeDto == null)
                return StatusCode.FAILURE;
            educationExperienceDto.setResume(resumeDto);
            educationExperienceDto.setDegree(educationExperienceVo.getDegree());
            educationExperienceDto.setEndTime(educationExperienceVo.getEndTime());
            educationExperienceDto.setStartTime(educationExperienceVo.getStartTime());
            educationExperienceDto.setMajor(educationExperienceVo.getMajor());
            educationExperienceDto.setSchool(educationExperienceVo.getSchool());
            educationExperienceDto.setTypeOfStudy(educationExperienceVo.getTypeOfStudy());
            educationExperienceRepository.save(educationExperienceDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    public int updateEducationExperience(EducationExperienceVo educationExperienceVo) {
        EducationExperienceDto educationExperienceDto;

        try {
            educationExperienceDto = educationExperienceRepository
                    .findEducationExperienceDtoByEducationExperienceId(educationExperienceVo.getEducationExperienceId());
            educationExperienceDto.setDegree(educationExperienceVo.getDegree());
            educationExperienceDto.setEndTime(educationExperienceVo.getEndTime());
            educationExperienceDto.setStartTime(educationExperienceVo.getStartTime());
            educationExperienceDto.setMajor(educationExperienceVo.getMajor());
            educationExperienceDto.setSchool(educationExperienceVo.getSchool());
            educationExperienceDto.setTypeOfStudy(educationExperienceVo.getTypeOfStudy());
            educationExperienceRepository.save(educationExperienceDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public int updateWorkExperience(WorkExperienceVo workExperienceVo) {
        WorkExperienceDto workExperienceDto;

        try {
            workExperienceDto = workExperienceRepository
                    .findWorkExperienceDtoByWorkExperienceId(workExperienceVo.getWorkExperienceId());
            workExperienceDto.setAchievement(workExperienceVo.getAchievement());
            workExperienceDto.setCity(workExperienceVo.getCity());
            workExperienceDto.setCompany(workExperienceVo.getCompany());
            workExperienceDto.setStartTime(workExperienceVo.getStartTime());
            workExperienceDto.setEndTime(workExperienceVo.getEndTime());
            workExperienceDto.setPost(workExperienceVo.getPost());
            workExperienceDto.setReferenceName(workExperienceVo.getReferenceName());
            workExperienceDto.setReferencePhoneNum(workExperienceVo.getReferencePhoneNum());
            workExperienceDto.setWorkDiscription(workExperienceVo.getWorkDescription());
            workExperienceRepository.save(workExperienceDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public WorkExperienceVo findWorkExperienceByWorkExperienceId(int workExperienceId) {
        WorkExperienceDto workExperienceDto;

        try {
            workExperienceDto = workExperienceRepository.findWorkExperienceDtoByWorkExperienceId(workExperienceId);
            if(workExperienceDto == null)
                return null;

        } catch (DataAccessException e) {
            return null;
        }
        return DataTransferUtil.WorkExperienceDtoToVo(workExperienceDto);
    }

    public EducationExperienceVo findEducationExperienceByEducationExperienceId(int educationExperienceId) {
        EducationExperienceDto educationExperienceDto;

        try {
            educationExperienceDto = educationExperienceRepository
                    .findEducationExperienceDtoByEducationExperienceId(educationExperienceId);
            if(educationExperienceDto == null)
                return null;
        } catch (DataAccessException e) {
            return null;
        }
        return DataTransferUtil.EducationExperienceDtoToVo(educationExperienceDto);
    }

    @Transactional
    public int deleteWorkExperienceByWorkExperienceId(int workExperienceId) {
        try {
            workExperienceRepository.deleteWorkExperienceDtoByWorkExperienceId(workExperienceId);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    @Transactional
    public int deleteEducationExperienceByEducationExperienceId(int educationExperienceId) {
        try {
            educationExperienceRepository.deleteEducationExperienceDtoByEducationExperienceId(educationExperienceId);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    public List<WorkExperienceVo> listWorkExperiencesByResumeId(int resumeId) {
        ResumeDto resumeDto;
        List<WorkExperienceDto> workExperienceDtos;
        List<WorkExperienceVo> workExperienceVos = new ArrayList<>();
        try {
            resumeDto = resumeDtoRepository.findResumeDtoByResumeId(resumeId);
            if(resumeDto == null)
                return new ArrayList<>();

            Hibernate.initialize(resumeDto.getWorkExperiences());
            workExperienceDtos = resumeDto.getWorkExperiences();

            if(workExperienceDtos == null)
                return new ArrayList<>();

            for(WorkExperienceDto w: workExperienceDtos) {
                workExperienceVos.add(DataTransferUtil.WorkExperienceDtoToVo(w));
            }
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return workExperienceVos;
    }

    public List<EducationExperienceVo> listEducationExperiencesByResumeId(int resumeId) {
        ResumeDto resumeDto;
        List<EducationExperienceDto> educationExperienceDtos;
        List<EducationExperienceVo> educationExperienceVos = new ArrayList<>();

        try {
            resumeDto = resumeDtoRepository.findResumeDtoByResumeId(resumeId);
            if(resumeDto == null)
                return new ArrayList<>();

            Hibernate.initialize(resumeDto.getEducationExperiences());
            educationExperienceDtos = resumeDto.getEducationExperiences();

            if(educationExperienceDtos == null)
                return new ArrayList<>();

            for(EducationExperienceDto e: educationExperienceDtos) {
                educationExperienceVos.add(DataTransferUtil.EducationExperienceDtoToVo(e));
            }
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return educationExperienceVos;
    }


    @Transactional
    public ResumeVo findResumeByApplicantId(int applicantId) {
        ApplicantDto applicantDto;
        ResumeDto resumeDto;
        try {
            applicantDto = applicantDtoRepository.findApplicantDtoByApplicantId(applicantId);
            entityManager.refresh(applicantDto);

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

    public int createResume(int applicantId) {
        ApplicantDto applicantDto;
        ResumeDto resumeDto;
        try {
            applicantDto = applicantDtoRepository.findApplicantDtoByApplicantId(applicantId);
            if(applicantDto.getResume() != null)
                return StatusCode.FAILURE;
            resumeDto = new ResumeDto();
            resumeDto.setApplicantName(applicantDto.getUser().getUserName());
            resumeDto.setApplicant(applicantDto);
            applicantDto.setResume(resumeDto);
            resumeDtoRepository.save(resumeDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
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

            resumeDtoRepository.save(resumeDto);

        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return  StatusCode.SUCCESS;
    }

    public boolean isResumeComplete(int resumeId, int modelId) {
        ResumeModelDto resumeModelDto;
        ResumeDto resumeDto;

        try {
            resumeModelDto = resumeModelDtoRepository.findResumeModelDtoByResumeModelId(modelId);
            resumeDto = resumeDtoRepository.findResumeDtoByResumeId(resumeId);
            if(resumeDto == null)
                return false;
        } catch (DataAccessException e) {
            return false;
        }

        if(resumeModelDto.isApplicantName() && resumeDto.getApplicantName() == null)
            return false;
        if(resumeModelDto.isApplicantSex() && resumeDto.getApplicantSex() < 0)
            return false;
        if(resumeModelDto.isApplicantAge() && resumeDto.getApplicantAge() < 0)
            return false;
        if(resumeModelDto.isApplicantSchool() && resumeDto.getApplicantSchool() == null)
            return false;
        if(resumeModelDto.isApplicantDegree() && resumeDto.getApplicantDegree() < 0)
            return false;
        if(resumeModelDto.isApplicantMajor() && resumeDto.getApplicantMajor() == null)
            return false;
        if(resumeModelDto.isApplicantCity() && resumeDto.getApplicantCity() == null)
            return false;
        if(resumeModelDto.isApplicantEmail() && resumeDto.getApplicantEmail() == null)
            return false;
        if(resumeModelDto.isApplicantPhone() && resumeDto.getApplicantPhone() == null)
            return false;
        if(resumeModelDto.isApplicantCV() && resumeDto.getApplicantCV() == null)
            return false;
        if(resumeModelDto.isApplicantHometown() && resumeDto.getApplicantHometown() == null)
            return false;
        if(resumeModelDto.isApplicantNation() && resumeDto.getApplicantNation() == null)
            return false;
        if(resumeModelDto.isApplicantPoliticalStatus() && resumeDto.getApplicantPoliticalStatus() == null)
            return false;
        if(resumeModelDto.isApplicantMaritalStatus() && resumeDto.getApplicantMaritalStatus() == null)
            return false;
        if(resumeModelDto.isApplicantDateOfBirth() && resumeDto.getApplicantDateOfBirth() == null)
            return false;
        if(resumeModelDto.isApplicantTimeToWork() && resumeDto.getApplicantTimeToWork() == null)
            return false;
        if(resumeModelDto.isApplicantCurrentSalary() && resumeDto.getApplicantCurrentSalary() < 0)
            return false;
        if(resumeModelDto.isApplicantExpectSalary() && resumeDto.getApplicantExpectSalary() < 0)
            return false;
        if(resumeModelDto.isApplicantDutyTime() && resumeDto.getApplicantDutyTime() == null)
            return false;
        if(resumeModelDto.isRecommenderName() && resumeDto.getRecommenderName() == null)
            return false;
        if(resumeModelDto.isApplicantAddress() && resumeDto.getApplicantAddress() == null)
            return false;
        if(resumeModelDto.isApplicantSelfIntro() && resumeDto.getApplicantSelfIntro() == null)
            return false;
        if(resumeModelDto.isApplicantPhoto() && resumeDto.getApplicantPhoto() == null)
            return false;
        if(resumeModelDto.isApplicantDegreePhoto() && resumeDto.getApplicantDegreePhoto() == null)
            return false;
        if(resumeModelDto.isFamilyContactRelation() && resumeDto.getFamilyContactRelation() == null)
            return false;
        if(resumeModelDto.isFamilyContactName() && resumeDto.getFamilyContactName() == null)
            return false;
        if(resumeModelDto.isFamilyContactCompany() && resumeDto.getFamilyContactCompany() == null)
            return false;
        if(resumeModelDto.isFamilyContactPhoneNum() && resumeDto.getFamilyContactPhoneNum() == null)
            return false;

        return true;
    }

    public List<ApplicantInterviewVo> findApplicantInterviewsByApplicationId(int applicationId) {
        ApplicationDto applicationDto;
        List<InterviewDto> interviewDtos;
        List<ApplicantInterviewVo> applicantInterviewVos = new ArrayList<>();

        try {
            applicationDto = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            interviewDtos = applicationDto.getInterviews();
            interviewDtos = interviewDtos.stream().filter(x -> (x.getReservationStatus() == ReservationStatus.SUCCESS))
                    .sorted(Comparator.comparing(x -> x.getCreateTime())).collect(Collectors.toList());
            for(InterviewDto i: interviewDtos) {
                applicantInterviewVos.add(DataTransferUtil.InterviewDtoToApplicantInterviewVo(i));
            }
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return applicantInterviewVos;
    }

    public ApplicationVo findApplicationByApplicationId(int applicationId) {
        ApplicationDto applicationDto;

        try {
            applicationDto = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            return DataTransferUtil.ApplicationDtoToVo(applicationDto);
        } catch (DataAccessException e) {
            return null;
        }
    }



    private int updateApplicationStatus(int applicationId, int status) {
        ApplicationDto applicationDto;

        try {
            applicationDto = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            applicationDto.setStatus(status);
            applicationDto.setApplicantFeedbackTime(Calendar.getInstance().getTime());
            applicationDtoRepository.save(applicationDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return  StatusCode.SUCCESS;
    }

    public int acceptOfferByApplicationId(int applicationId) {
        return updateApplicationStatus(applicationId, ApplicationStatus.APPLICANT_ACCEPT);
    }

    public int rejectOfferByApplicationId(int applicationId) {
        return updateApplicationStatus(applicationId, ApplicationStatus.APPLICANT_REJECT);
    }

}
