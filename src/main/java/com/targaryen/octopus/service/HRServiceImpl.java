package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dto.ApplicationDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.vo.ApplicationHRVo;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author He Junfeng
 */
@Service
public class HRServiceImpl implements HRService {
    private PostDtoRepository postDtoRepository;

    @Autowired
    public HRServiceImpl(DaoFactory daoFactory) {
        this.postDtoRepository = daoFactory.getPostDtoRepository();
    }

    @Override
    public List<PostVo> listPosts() {
        return postDtoRepository.findUnrevokedPosts().stream()
                .map(n -> new PostVo.Builder()
                        .postId(n.getPostId())
                        .postName(n.getPostName())
                        .postType(n.getPostType())
                        .postLocale(n.getPostLocale())
                        .postDescription(n.getPostDescription())
                        .postRequirement(n.getPostRequirement())
                        .status(n.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PostVo findPostById(int postId) {
        PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
        if(postDto == null) {
            return new PostVo.Builder().build();
        } else {
            return new PostVo.Builder()
                    .postId(postDto.getPostId())
                    .postName(postDto.getPostName())
                    .postType(postDto.getPostType())
                    .postLocale(postDto.getPostLocale())
                    .postDescription(postDto.getPostDescription())
                    .postRequirement(postDto.getPostRequirement())
                    .status(postDto.getStatus())
                    .build();
        }
    }

    @Override
    public void publishPostById(int postId) {
        PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
        postDto.setStatus(1);
        postDtoRepository.save(postDto);
    }

    @Override
    public void closePostById(int postId) {
        PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
        postDto.setStatus(0);
        postDtoRepository.save(postDto);
    }

    @Override
    public void updatePost(PostDto updatePost) {
        PostDto post = postDtoRepository.findPostDtoByPostId(updatePost.getPostId());
        if(post != null) {
            post.setPostName(updatePost.getPostName());
            post.setPostType(updatePost.getPostType());
            post.setPostLocale(updatePost.getPostLocale());
            post.setPostDescription(updatePost.getPostDescription());
            post.setPostRequirement(updatePost.getPostRequirement());
            postDtoRepository.save(post);
        }
    }

    @Override
    public List<ApplicationHRVo> findApplicationsByPostId(int postId) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        if(post != null) {
            return post.getApplications().stream()
                    .map(n -> new ApplicationHRVo.Builder()
                            .applicationId(n.getApplicationId())
                            .status(n.getStatus())
                            .applicantId(n.getApplicant().getApplicantId())
                            .postId(n.getPost().getPostId()).build())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<ApplicationHRVo>();
        }
    }
}
