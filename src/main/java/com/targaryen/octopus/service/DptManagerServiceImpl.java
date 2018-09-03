package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.DptManagerDtoRepository;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dto.DptManagerDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return dptManagerDtoRepository.findDptManagerDtoByDptManagerId(dptManagerId).getPosts()
                .stream()
                .map(n -> new PostVo.Builder()
                        .postId(n.getPostId())
                        .postName(n.getPostName())
                        .postDescription(n.getPostDescription())
                        .requirement(n.getRequirement())
                        .status(n.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PostVo findPostById(int postId) {
        PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
        return new PostVo.Builder()
                .postId(postDto.getPostId())
                .postName(postDto.getPostName())
                .postDescription(postDto.getPostDescription())
                .requirement(postDto.getRequirement())
                .status(postDto.getStatus())
                .build();
    }

    @Override
    public void createNewPost(PostDto newPost) {
        postDtoRepository.save(newPost);
    }

    @Override
    public void updatePost(PostDto post) {
        postDtoRepository.save(post);
    }

    @Override
    public void deletePost(int postId) {
        postDtoRepository.deletePostDtoByPostId(postId);
    }

    @Override
    public void saveDptManager(DptManagerDto dptManager) {
        dptManagerDtoRepository.save(dptManager);
    }
}
