package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.*;

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
     * find applications by postId
     *
     */
    List<ApplicationResumeVo> findApplicationsByPostId(int postId);

    /**
     * find resume by applicationId
     *
     */
    ResumeVo findResumeByApplicationId(int applicationId);

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
     * create an interview, interviewVo should include applicationId, interviewerId, startTime, interviewPlace
     *
     */
    int createInterview(InterviewVo interviewVo);

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
}
