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
    void createNewPost(PostDto newPost);

    /**
     * update post
     *
     */
    void updatePost(PostDto updatePost);

    /**
     * delete post
     *
     */
    void deletePost(int postId);

    /**
     * revoke post by postId
     *
     */
    void revokePost(int postId);

    /**
     * save DptManager
     *
     */
    void saveDptManager(DptManagerDto dptManager);


}
