package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.ApplicationResumeVo;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.PostVo;
import com.targaryen.octopus.vo.ResumeVo;

import java.util.List;

/**
 * @author He Junfeng
 */
public interface DptManagerService {
    /**
     * find posts by userId
     *
     */
    List<PostVo> findPostsByUserId(int userId);

    /**
     * find posts by Id
     *
     */
    PostVo findPostById(int postId);

    /**
     * create new post, newPost Vo should include postName, recruitType
     *
     */
    int createNewPost(PostVo newPost, int userId);

    /**
     * update post
     *
     */
    int updatePost(PostVo updatePost);

    /**
     * delete post
     *
     */
    int deletePost(int postId);

    /**
     * revoke post by postId
     *
     */
    int revokePost(int postId);

    /**
     * find interview passed applications by postId
     *
     */
    List<ApplicationResumeVo> findInterviewPassedApplicationsByPostId(int postId);

    /**
     * find ApplicationResumeVo by applicationId
     *
     */
    ApplicationResumeVo findApplicationResumeVoByApplicationId(int applicationId);

    /**
     * dpt approve pass application by applicationId
     *
     */
    int dptApprovePassApplicationById(int applicationId);

    /**
     * dpt approve fail application by applicationId
     *
     */
    int dptApproveFailApplicationById(int applicationId);

}
