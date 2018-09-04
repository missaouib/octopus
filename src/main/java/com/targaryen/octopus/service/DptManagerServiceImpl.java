package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.DptManagerDtoRepository;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dto.DptManagerDto;
import com.targaryen.octopus.dto.PostDto;
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
public class DptManagerServiceImpl implements DptManagerService {
    private DptManagerDtoRepository dptManagerDtoRepository;
    private PostDtoRepository postDtoRepository;

    @Autowired
    public DptManagerServiceImpl(DaoFactory daoFactory) {
        this.dptManagerDtoRepository = daoFactory.getDptManagerDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
    }

    @Override
    public List<PostVo> findPostsByDptManagerId(int dptManagerId) {
        DptManagerDto dptManager = dptManagerDtoRepository.findDptManagerDtoByDptManagerId(dptManagerId);
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
                    .status(postDto.getStatus())
                    .build();
        }
    }

    @Override
    public void createNewPost(PostDto newPost) {
        postDtoRepository.save(newPost);
    }

    @Override
    public void updatePost(PostDto updatePost) {
        postDtoRepository.save(updatePost);
    }

    @Override
    public void deletePost(int postId) {
        postDtoRepository.deletePostDtoByPostId(postId);
    }

    @Override
    public void revokePost(int postId) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        if(post != null) {
            post.setStatus(-1);
            postDtoRepository.save(post);
        }
    }

    @Override
    public void saveDptManager(DptManagerDto dptManager) {
        dptManagerDtoRepository.save(dptManager);
    }
}
