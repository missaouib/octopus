package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Created by Liu Mengyang on 2018/09/04
 */
@Service
public class PublicServiceImpl implements PublicService {
    private PostDtoRepository postDtoRepository;

    @Autowired
    public PublicServiceImpl(DaoFactory daoFactory) {
        this.postDtoRepository = daoFactory.getPostDtoRepository();
    }

    @Override
    public List<PostVo> listPostsByStatus(int status) {
        List<PostDto> postList = postDtoRepository.findPostDtoByStatus(PostStatus.POSTED);

        return postList.stream().map( x -> new PostVo.Builder()
                .postId(x.getPostId())
                .postLocale(x.getPostLocale())
                .postName(x.getPostName())
                .postRequirement(x.getPostRequirement())
                .postType(x.getPostType())
                .postDescription(x.getPostDescription())
                .status(x.getStatus()).build()).collect(Collectors.toList());
    }

    @Override
    public PostVo findPostById(int id) {
        PostDto postDto = postDtoRepository.findPostDtoByPostId(id);

        return new PostVo.Builder()
                .postId(postDto.getPostId())
                .postLocale(postDto.getPostLocale())
                .postName(postDto.getPostName())
                .postRequirement(postDto.getPostRequirement())
                .postType(postDto.getPostType())
                .postDescription(postDto.getPostDescription())
                .status(postDto.getStatus()).build();
    }
}
