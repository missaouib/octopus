package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.*;

import java.util.List;

/**
 * Created by Liu Mengyang on 2018/09/05
 */
public interface InterviewerService {
//    List<InterviewVo> listInterviewsByInterviewerId(int interviewerId);

    /**
     * Find resume by applicationId
     *
     * @param applicationId
     * Application Id
     *
     * @return
     * Resume of the application
     */
    ResumeVo findResumeByApplicationId(int applicationId);
//    ResumeVo findResumeByInterviewId(int interviewId);

    /**
     * Update interviewer status of interview
     *
     * @param interviewerStatus
     * New interviewer status
     *
     * @param interviewId
     * Interview Id
     *
     * @param comment
     * If reject, this field records the reject reason
     *
     * @return
     * Execution status
     */
    int setInterviewerStatus(int interviewerStatus, int interviewId, String comment);
//    List<InterviewVo> listUnreplyedInterviewsByInterviewerId(int interviewerId);
//    List<InterviewVo> listActiveInterviewsByInterviewerId(int interviewerId);

    /**
     * List interviews' detail by interviewerId
     *
     * @param interviewerId
     * Interviewer Id
     *
     * @return
     * List of interviews's detail
     */
    List<InterviewerInterviewVo> listInterviewerInterviewsByInterviewerId(int interviewerId);

    /**
     * Set interviewer evaluation comment for interview
     *
     * @param interviewId
     * Interview Id
     *
     * @param comment
     * Evaluation comment
     *
     * @return
     * Execution status
     */
    int setInterviewResultComment(int interviewId, String comment);
//    List<ApplicationResumeVo> findApplicationsByPostId(int postId);
//    ApplicationResumeVo findApplicationResumeVoByApplicationId(int applicationId);
//    List<ApplicationResumeVo> findApplicationsByPostIdAndStatus(int postId, Integer status);

    /**
     * List interviews' detail by applicationId
     *
     * @param applicationId
     * Application Id
     *
     * @return
     * List of interviews's detail
     */
    List<InterviewerInterviewVo> findInterviewerInterviewsByApplicationId(int applicationId);

    /**
     * Set interview result grade
     *
     * @param interviewId
     * Interview Id
     *
     * @param resultStatus
     * Result grade
     *
     * @return
     * Execution status
     */
    int setInterviewResultStatus(int interviewId, int resultStatus);

    /**
     * Find application by application Id
     *
     * @param applicationId
     * Application Id
     *
     * @return
     * Application value object
     */
    ApplicationVo findApplicationByApplicationId(int applicationId);
}
