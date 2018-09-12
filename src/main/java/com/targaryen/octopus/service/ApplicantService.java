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
     * @param resumeVo
     * resumeVo of saved resume, field 'applicantName' must not be blank
     *
     * @return
     * Execution status
     */
    int SaveResume(ResumeVo resumeVo);

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

    /**
     *
     * @param workExperienceVo
     * must set the field "resumeId",
     * field "workExperienceId" should be left unset
     *
     * @return
     */
    int createWorkExperience(WorkExperienceVo workExperienceVo);

    /**
     *
     * @param educationExperienceVo
     *  must set the field "resumeId"
     *  field "educationExperienceId" should be left unset
     *
     * @return
     */
    int createEducationExperience(EducationExperienceVo educationExperienceVo);

    /**
     *
     * @param educationExperienceVo
     * must set the field "educationExperienceId"
     *
     * @return
     */
    int updateEducationExperience(EducationExperienceVo educationExperienceVo);

    /**
     *
     * @param workExperienceVo
     * must set the field "workExperienceId"
     *
     * @return
     */
    int updateWorkExperience(WorkExperienceVo workExperienceVo);

    /**
     *
     * @param workExperienceId
     * @return
     */
    WorkExperienceVo findWorkExperienceByWorkExperienceId(int workExperienceId);

    /**
     *
     * @param educationExperienceId
     * @return
     */
    EducationExperienceVo findEducationExperienceByEducationExperienceId(int educationExperienceId);

    /**
     *
     * @param workExperienceId
     * @return
     */
    int deleteWorkExperienceByWorkExperienceId(int workExperienceId);

    /**
     *
     * @param educationExperienceId
     * @return
     */
    int deleteEducationExperienceByEducationExperienceId(int educationExperienceId);

    /**
     *
     * @param resumeId
     * @return
     */
    List<WorkExperienceVo> listWorkExperiencesByResumeId(int resumeId);

    /**
     *
     * @param resumeId
     * @return
     */
    List<EducationExperienceVo> listEducationExperiencesByResumeId(int resumeId);

    /**
     *
     * @param applicantId
     * @return
     */
    ResumeVo findResumeByApplicantId(int applicantId);

    /**
     *
     * @param applicantId
     * @return
     */
    int createResume(int applicantId);

    /**
     *
     * @param resumeVo
     * @param modelId
     * @return
     */
    int saveResumeWithModel(ResumeVo resumeVo, int modelId);

    /**
     *
     * @param resumeId
     * @param modelId
     * @return
     */
    boolean isResumeComplete(int resumeId, int modelId);
}
