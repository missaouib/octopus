package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;

import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.ApplicationStatus;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.ApplicationResumeVo;
import com.targaryen.octopus.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author He Junfeng
 */
@Service
public class DptManagerServiceImpl implements DptManagerService {
    private PostDtoRepository postDtoRepository;
    private UserDtoRepository userDtoRepository;
    private ApplicationDtoRepository applicationDtoRepository;
    private ResumeModelDtoRepository resumeModelDtoRepository;

    @Autowired
    public DptManagerServiceImpl(DaoFactory daoFactory) {
        this.applicationDtoRepository = daoFactory.getApplicationDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.userDtoRepository = daoFactory.getUserDtoRepository();
        this.resumeModelDtoRepository = daoFactory.getResumeModelDtoRepository();
    }

    @Override
    public List<PostVo> findPostsByUserId(int userId) {
        DptManagerDto dptManager = userDtoRepository.findUserDtoByUserId(userId).getDptManager();
        if(dptManager == null) {
            return new ArrayList<PostVo>();
        } else {
            return dptManager.getDepartment().getPosts().stream()
                    .map(n -> DataTransferUtil.PostDtoToVo(n))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public PostVo findPostById(int postId) {
        PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
        if(postDto == null) {
            return new PostVo.Builder().build();
        } else {
            return DataTransferUtil.PostDtoToVo(postDto);
        }
    }

    @Override
    public int createNewPost(PostVo newPost, int userId) {
        try {
            DepartmentDto department = userDtoRepository.findUserDtoByUserId(userId).getDptManager().getDepartment();
            PostDto postDto = new PostDto();
            postDto.setPostId(newPost.getPostId());
            postDto.setPostName(newPost.getPostName());
            postDto.setPostType(newPost.getPostType());
            postDto.setPostLocale(newPost.getPostLocale());
            postDto.setPostDescription(newPost.getPostDescription());
            postDto.setPostRequirement(newPost.getPostRequirement());
            postDto.setRecruitNum(newPost.getRecruitNum());
            postDto.setRecruitDpt(newPost.getRecruitDpt());
            postDto.setRecruitType(newPost.getRecruitType());
            postDto.setStatus(newPost.getStatus());
            postDto.setDepartment(department);
            postDtoRepository.save(postDto);
            ResumeModelDto resumeModelDto = new ResumeModelDto();
            resumeModelDto.setPost(postDto);
            postDto.setResumeModel(resumeModelDto);
            resumeModelDtoRepository.save(resumeModelDto);
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
                DataTransferUtil.updatePostDtoByVo(post, updatePost);
                postDtoRepository.save(post);
            }
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Transactional
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

    @Override
    public List<ApplicationResumeVo> findInterviewPassedApplicationsByPostId(int postId) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        List<ApplicationDto> applicationDtos = post.getApplications().stream()
                .filter(n ->
                        ApplicationStatus.INTERVIEW_PASS.equals(n.getStatus())
                        || Math.abs(n.getStatus()) >= 3)
                .collect(Collectors.toList());
        return applicationDtos.stream()
                .map(n -> DataTransferUtil.ApplicationDtoToApplicationResumeVo(n))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResumeVo findApplicationResumeVoByApplicationId(int applicationId) {
        ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
        ApplicationResumeVo applicationResumeVo;
        if(application != null) {
            applicationResumeVo = DataTransferUtil.ApplicationDtoToApplicationResumeVo(application);
        } else {
            applicationResumeVo = null;
        }
        return applicationResumeVo;
    }

    @Override
    public int dptApprovePassApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            application.setStatus(ApplicationStatus.DPT_PASS);
            application.setDptApproveEndTime(Calendar.getInstance().getTime());
            applicationDtoRepository.save(application);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public int dptApproveFailApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            application.setStatus(ApplicationStatus.DPT_FAIL);
            application.setDptApproveEndTime(Calendar.getInstance().getTime());
            applicationDtoRepository.save(application);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

}
