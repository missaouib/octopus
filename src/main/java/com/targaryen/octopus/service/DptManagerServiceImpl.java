package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;

import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.ApplicationStatus;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.PostVo;
import com.targaryen.octopus.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

    @Autowired
    public DptManagerServiceImpl(DaoFactory daoFactory) {
        this.applicationDtoRepository = daoFactory.getApplicationDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.userDtoRepository = daoFactory.getUserDtoRepository();
    }

    @Override
    public List<PostVo> findPostsByUserId(int userId) {
        DptManagerDto dptManager = userDtoRepository.findUserDtoByUserId(userId).getDptManager();
        if(dptManager == null) {
            return new ArrayList<PostVo>();
        } else {
            return dptManager.getPosts().stream()
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

    @Override
    public List<ApplicationVo> findPassedApplicationsByPostId(int postId) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        List<ApplicationDto> applicationDtos = post.getApplications().stream()
                .filter(n -> ApplicationStatus.INTERVIEW_PASS.equals(n.getStatus()))
                .collect(Collectors.toList());
        return applicationDtos.stream()
                .map(n -> new ApplicationVo.Builder()
                        .applicationId(n.getApplicationId())
                        .status(n.getStatus())
                        .applicantId(n.getApplicant().getApplicantId())
                        .postId(n.getPost().getPostId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ResumeVo findResumeByApplicationId(int applicationId) {
        ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
        ApplicantDto applicant = application.getApplicant();
        ResumeDto resumeDto;
        if(applicant != null) {
            resumeDto = applicant.getResume();
        } else {
            resumeDto = null;
        }
        ResumeVo resumeVo;
        if(resumeDto != null) {
            resumeVo = new ResumeVo.Builder()
                    .resumeId(resumeDto.getResumeId())
                    .applicantName(resumeDto.getApplicantName())
                    .applicantSex(resumeDto.getApplicantSex())
                    .applicantAge(resumeDto.getApplicantAge())
                    .applicantSchool(resumeDto.getApplicantSchool())
                    .applicantDegree(resumeDto.getApplicantDegree())
                    .applicantMajor(resumeDto.getApplicantMajor())
                    .applicantCity(resumeDto.getApplicantCity())
                    .applicantEmail(resumeDto.getApplicantEmail())
                    .applicantPhone(resumeDto.getApplicantPhone())
                    .applicantCV(resumeDto.getApplicantCV())
                    .build();
        } else {
            resumeVo = null;
        }
        return resumeVo;
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
