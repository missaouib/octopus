package com.targaryen.octopus.service;

import com.targaryen.octopus.dto.DptManagerDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.vo.PostVo;

import java.util.List;

/**
 * @author He Junfeng
 */
public interface DptManagerService {
    /**
     * find posts by DptManagerId
     *
     */
    List<PostVo> findPostsByDptManagerId(int dptManagerId);

    /**
     * find posts by Id
     *
     */
    PostVo findPostById(int postId);

    /**
     * create new post
     *
     */
    int createNewPost(PostVo newPost, int dptManagerId);

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


}
