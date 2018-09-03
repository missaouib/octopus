package com.targaryen.octopus.service;

import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.vo.ApplicationHRVo;
import com.targaryen.octopus.vo.PostVo;

import java.util.List;

/**
 * @author He Junfeng
 */
public class HRServiceImpl implements HRService {

    @Override
    public List<PostVo> listPosts() {
        return null;
    }

    @Override
    public PostVo findPostById() {
        return null;
    }

    @Override
    public void publishPostById(int postId) {

    }

    @Override
    public void closePostById(int postId) {

    }

    @Override
    public void updatePost(PostDto updatePost) {

    }

    @Override
    public List<ApplicationHRVo> findApplicationsByPostId(int postId) {
        return null;
    }
}
