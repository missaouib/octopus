package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.DptManagerDtoRepository;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dao.UserDtoRepository;
import com.targaryen.octopus.dto.DptManagerDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author He Junfeng
 */
@Service
public class DptManagerServiceImpl implements DptManagerService {
    private DptManagerDtoRepository dptManagerDtoRepository;
    private PostDtoRepository postDtoRepository;
    private UserDtoRepository userDtoRepository;

    @Autowired
    public DptManagerServiceImpl(DaoFactory daoFactory) {
        this.dptManagerDtoRepository = daoFactory.getDptManagerDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.userDtoRepository = daoFactory.getUserDtoRepository();
    }

    @Override
    public List<PostVo> findPostsByDptManagerId(int userId) {
        DptManagerDto dptManager = userDtoRepository.findUserDtoByUserId(userId).getDptManager();
        if(dptManager == null) {
            return new ArrayList<PostVo>();
        } else {
            return dptManager.getPosts().stream()
                    .map(n -> new PostVo.Builder()
                            .postId(n.getPostId())
                            .postName(n.getPostName())
                            .postType(n.getPostType())
                            .postLocale(n.getPostLocale())
                            .postDescription(n.getPostDescription())
                            .postRequirement(n.getPostRequirement())
                            .recruitNum(n.getRecruitNum())
                            .recruitDpt(n.getRecruitDpt())
                            .publishTime(n.getPublishTime())
                            .status(n.getStatus())
                            .build())
                    .collect(Collectors.toList());
        }
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
                    .recruitNum(postDto.getRecruitNum())
                    .recruitDpt(postDto.getRecruitDpt())
                    .publishTime(postDto.getPublishTime())
                    .status(postDto.getStatus())
                    .build();
        }
    }

    @Override
    public int createNewPost(PostVo newPost, int userId) {
        try {
            DptManagerDto dptManager = userDtoRepository.findUserDtoByUserId(userId).getDptManager();
            PostDto postDto = new PostDto();
            postDto.setPostId(newPost.getPostId());
            postDto.setPostName(newPost.getPostName());
            postDto.setPostType(newPost.getPostType());
            postDto.setPostLocale(newPost.getPostLocale());
            postDto.setPostDescription(newPost.getPostDescription());
            postDto.setPostRequirement(newPost.getPostRequirement());
            postDto.setRecruitNum(newPost.getRecruitNum());
            postDto.setRecruitDpt(newPost.getRecruitDpt());
            postDto.setStatus(newPost.getStatus());
            postDto.setDptManager(dptManager);
            postDtoRepository.save(postDto);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public int updatePost(PostVo updatePost) {
        try {
            PostDto post = postDtoRepository.findPostDtoByPostId(updatePost.getPostId());
            if(post != null) {
                post.setPostName(updatePost.getPostName());
                post.setPostType(updatePost.getPostType());
                post.setPostLocale(updatePost.getPostLocale());
                post.setPostDescription(updatePost.getPostDescription());
                post.setPostRequirement(updatePost.getPostRequirement());
                post.setRecruitNum(updatePost.getRecruitNum());
                post.setRecruitDpt(updatePost.getRecruitDpt());
                postDtoRepository.save(post);
            }
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public int deletePost(int postId) {
        try {
            postDtoRepository.deletePostDtoByPostId(postId);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public int revokePost(int postId) {
        try {
            PostDto post = postDtoRepository.findPostDtoByPostId(postId);
            if(post != null) {
                post.setStatus(PostStatus.REVOKED);
                postDtoRepository.save(post);
            }
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }
}
