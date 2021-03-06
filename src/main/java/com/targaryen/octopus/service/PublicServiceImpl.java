package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.DepartmentDtoRepository;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dto.DepartmentDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.DepartmentVo;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Created by Liu Mengyang on 2018/09/04
 */
@Service
public class PublicServiceImpl implements PublicService {
    private PostDtoRepository postDtoRepository;
    private DepartmentDtoRepository departmentDtoRepository;

    @Autowired
    public PublicServiceImpl(DaoFactory daoFactory) {
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.departmentDtoRepository = daoFactory.getDepartmentDtoRepository();
    }

    @Override
    public List<PostVo> listPostsByStatus(int status) {
        List<PostDto> postList = postDtoRepository.findAllByStatusOrderByPostIdDesc(PostStatus.PUBLISHED);

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

        return DataTransferUtil.PostDtoToVo(postDto);
    }

    public List<DepartmentVo> findAllDepartments() {
        List<DepartmentDto> departmentDtos;
        List<DepartmentVo> departmentVos = new ArrayList<>();

        try {
            departmentDtos = departmentDtoRepository.findAll();
            for(DepartmentDto d: departmentDtos) {
                departmentVos.add(DataTransferUtil.DepartmentDtoToVo(d));
            }
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return departmentVos;
    }
}
