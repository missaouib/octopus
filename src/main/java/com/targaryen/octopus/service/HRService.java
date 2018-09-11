package com.targaryen.octopus.service;

import com.targaryen.octopus.dto.InterviewDto;
import com.targaryen.octopus.vo.*;

import java.util.Date;
import java.util.List;

/**
 * @author He Junfeng
 */
public interface HRService {
    /**
     * List posts
     *
     */
    List<PostVo> listPosts();

    /**
     * find post by postId
     *
     */
    PostVo findPostById(int postId);

    /**
     * Publish post by postId
     *
     */
    int publishPostById(int postId);

    /**
     * Close post by postId
     *
     */
    int closePostById(int postId);

    /**
     * Finish post by postId
     *
     */
    int finishPostById(int postId);

    /**
     * update post
     *
     */
    int updatePost(PostVo updatePost);

    /**
     * find resume model by postId
     *
     */
    ResumeModelVo findResumeModelVoByPostId(int postId);

    /**
     * update resume model by resumeModelId
     *
     */
    int updateResumeModelById(ResumeModelVo resumeModelVo);

    /**
     * find applications by postId
     *
     */
    List<ApplicationResumeVo> findApplicationsByPostId(int postId);

    /**
     * find ApplicationResumeVo by applicationId
     *
     */
    ApplicationResumeVo findApplicationResumeVoByApplicationId(int applicationId);

    /**
     * filter pass application by applicantId
     *
     */
    int filterPassApplicationById(int applicationId);

    /**
     * filter fail application by applicantId
     *
     */
    int filterFailApplicationById(int applicationId);

    /**
     * revoke filter pass application by applicantId
     *
     */
    int revokeFilterApplicationById(int applicationId);

    /**
     * find applications by postId and status
     *
     */
    List<ApplicationResumeVo> findApplicationsByPostIdAndStatus(int postId, Integer Status);

    /**
     * list interviewer
     *
     */
    List<InterviewerVo> listInterviewers();

    /**
     * create an interview, interviewVo should at least include postId, interviewerId, startTime, interviewPlace,
     * for society recruitment it should include applicationId
     *
     */
    int createInterview(InterviewVo interviewVo);

    /**
     * create a list of interviews,interviewVo should at least include postId, interviewerId, startTime, interviewPlace,
     * for society recruitment it should include applicationId
     */
    int createListOfInterviews(List<InterviewVo> interviewVos);

    /**
     * find interview by interviewId
     *
     */
    InterviewVo findInterviewById(int interviewId);

    /**
     * delete interview by interviewId
     *
     */
    int deleteInterviewById(int interviewId);

    /**
     * find interview by applicationId
     *
     */
    List<InterviewVo> findInterviewByApplicationId(int applicationId);

    /**
     * find list of interviews by and postId and date
     *
     */
    List<InterviewVo> findListOfInterviewsByPostIdAndTime(int postId, Date beginTime, Date endTime);

    /**
     * find list of interviews by postId and interviewRound
     *
     */
    List<InterviewVo> findListOfInterviewsByPostIdAndInterviewRound(int postId, int interviewRound);

    /**
     * add a new interview round by postId
     *
     */
    int addNewInterviewRoundByPostId(int postId);

    /**
     * interview pass application by applicationId
     *
     */
    int interviewPassApplicationById(int applicationId);

    /**
     * interview fail application by applicationId
     *
     */
    int interviewFailApplicationById(int applicationId);

    /**
     * send offer by applicationId
     *
     */
    int sendOfferByApplicationId(int applicationId);
}
