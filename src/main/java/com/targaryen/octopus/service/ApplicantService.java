package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.*;

import java.util.List;

/**
 *  Created by Liu Mengyang on 2018/09/05
 */
public interface ApplicantService {

    /**
     *
     * save applicant user resume, if no resume saved before it will create one first
     *
     * @param userId
     * userId of current applicant
     *
     * @param resumeVo
     * resumeVo of saved resume, field 'applicantName' must not be blank
     *
     * @return
     * Execution status
     */
    int SaveResume(int userId, ResumeVo resumeVo);

    /**
     *
     * find resume by applicant userId, so the userId must be of an applicant
     *
     * @param userId
     * applicant userId
     *
     * @return
     * resume of the applicant stored, if no resume found return null
     */
    ResumeVo findResumeByUserId(int userId);

    /**
     *
     * find all applications of a applicant by userId, so the userId must be of an applicant
     *
     * @param userId
     * applicant userId
     *
     * @return
     * list of applications, if no application found return empty list
     */
    List<ApplicantApplicationVo> findApplicationsByUserId(int userId);

    /**
     *
     * create a new post application based on applicationVo
     *
     * @param applicationVo
     * application information
     *
     * @return
     * execution status
     */
    int CreateNewApplication(ApplicationVo applicationVo);

    /**
     * find interviews are not replied by applicant, group by applicant's userId
     *
     * @param userId
     * applicant's userId
     *
     * @return
     * list of not replied interviews
     */
    List<InterviewVo> findUnreplyedInterviewsByUserId(int userId);

    /**
     * find interviews that are accepted by applicant, group by applicant's userId
     *
     * @param userId
     * applicant's userId
     *
     * @return
     * list of interviews accepted by applicant
     */
    List<InterviewVo> findAcceptedInterviewsByUserId(int userId);

    /**
     * Update applicant status of interview, once the status is updated to "accept" or "reject",
     * the interview reservation status is determined
     *
     * @param interviewId
     * InterviewId of the interview to be updated
     *
     * @param applicantStatus
     * New applicant status
     *
     * @param comment
     * If applicant accept the interview, this field will be left null. If applicant reject the interview,
     * this field will contain the reject reason
     *
     * @return
     */
    int updateApplicantStatusOfInterview(int interviewId, int applicantStatus, String comment);

    /**
     * find interviews' detail are not replied by applicant, group by applicant's userId
     *
     * @param userId
     * applicant's userId
     *
     * @return
     * list of not replied interviews
     */
    List<ApplicantInterviewVo> findUnreplyedInterviewDetailsByUserId(int userId);

    /**
     * find interviews' detail that are accepted by applicant, group by applicant's userId
     *
     * @param userId
     * applicant's userId
     *
     * @return
     * list of interviews accepted by applicant
     */
    List<ApplicantInterviewVo> findAcceptedInterviewDetailsByUserId(int userId);

    /**
     * find applicantInterviews by applicationId
     * @param applicationId
     * @return
     */
    List<ApplicantInterviewVo> findApplicantInterviewsByApplicationId(int applicationId);
    ApplicationVo findApplicationByApplicationId(int applicationId);
    int acceptOfferByApplicationId(int applicationId);
    int rejectOfferByApplicationId(int applicationId);
    ResumeModelVo findResumeModelByPostId(int postId);

    int createWorkExperience(WorkExperienceVo workExperienceVo);
    int createEducationExperience(EducationExperienceVo educationExperienceVo);
    int updateEducationExperience(EducationExperienceVo educationExperienceVo);
    int updateWorkExperience(WorkExperienceVo workExperienceVo);
    WorkExperienceVo findWorkExperienceByWorkExperienceId(int workExperienceId);
    EducationExperienceVo findEducationExperienceByEducationExperienceId(int educationExperienceId);
    int deleteWorkExperienceByWorkExperienceId(int workExperienceId);
    int deleteEducationExperienceByEducationExperienceId(int educationExperienceId);
    List<WorkExperienceVo> listWorkExperiencesByResumeId(int resumeId);
    List<EducationExperienceVo> listEducationExperiencesByResumeId(int resumeId);
    ResumeVo findResumeByApplicantId(int applicantId);
    int createResume(int applicantId);
    int saveResumeWithModel(ResumeVo resumeVo, int modelId);
    boolean isResumeComplete(int resumeId, int modelId);
}
