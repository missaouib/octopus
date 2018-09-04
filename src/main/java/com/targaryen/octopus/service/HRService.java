package com.targaryen.octopus.service;

import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.InterviewerVo;
import com.targaryen.octopus.vo.PostVo;
import com.targaryen.octopus.vo.ResumeVo;

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
     * update post
     *
     */
    int updatePost(PostVo updatePost);

    /**
     * find applications by postId
     *
     */
    List<ApplicationVo> findApplicationsByPostId(int postId);

    /**
     * find resume by applicantId
     *
     */
    ResumeVo findResumeByApplicantId(int applicantId);

    /**
     * filter application by applicantId
     *
     */
    int filterApplicationById(int applicationId);

    /**
     * list interviewer
     *
     */
    List<InterviewerVo> listInterviewers();

    /**
     * create an interview
     *
     */
    int createInterview(int applicationId, int interviewerId);
}
