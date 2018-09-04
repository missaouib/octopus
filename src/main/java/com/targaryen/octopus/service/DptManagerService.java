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
    int createNewPost(PostDto newPost);

    /**
     * update post
     *
     */
    int updatePost(PostDto updatePost);

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
     * save DptManager
     *
     */
    int saveDptManager(DptManagerDto dptManager);


}
