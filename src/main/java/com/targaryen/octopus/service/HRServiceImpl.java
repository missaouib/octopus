package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.ApplicantDtoRepository;
import com.targaryen.octopus.dao.ApplicationDtoRepository;
import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.PostDtoRepository;
import com.targaryen.octopus.dto.ApplicantDto;
import com.targaryen.octopus.dto.ApplicationDto;
import com.targaryen.octopus.dto.PostDto;
import com.targaryen.octopus.dto.ResumeDto;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.PostStatus;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.InterviewerVo;
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
public class HRServiceImpl implements HRService {
    private PostDtoRepository postDtoRepository;
    private ApplicantDtoRepository applicantDtoRepository;
    private ApplicationDtoRepository applicationDtoRepository;

    @Autowired
    public HRServiceImpl(DaoFactory daoFactory) {
        this.applicantDtoRepository = daoFactory.getApplicantDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.applicationDtoRepository = daoFactory.getApplicationDtoRepository();
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
                        .recruitNum(n.getRecruitNum())
                        .recruitDpt(n.getRecruitDpt())
                        .publishTime(n.getPublishTime())
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
                    .recruitNum(postDto.getRecruitNum())
                    .recruitDpt(postDto.getRecruitDpt())
                    .publishTime(postDto.getPublishTime())
                    .status(postDto.getStatus())
                    .build();
        }
    }

    @Override
    public int publishPostById(int postId) {
        try {
            PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
            postDto.setStatus(PostStatus.PUBLISHED);
            postDto.setPublishTime(Calendar.getInstance().getTime());
            postDtoRepository.save(postDto);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

    }

    @Override
    public int closePostById(int postId) {
        try {
            PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
            postDto.setStatus(PostStatus.INIT);
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
    public List<ApplicationVo> findApplicationsByPostId(int postId) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        if(post != null) {
            return post.getApplications().stream()
                    .map(n -> new ApplicationVo.Builder()
                            .applicationId(n.getApplicationId())
                            .status(n.getStatus())
                            .applicantId(n.getApplicant().getApplicantId())
                            .postId(n.getPost().getPostId()).build())
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<ApplicationVo>();
        }
    }

    @Override
    public ResumeVo findResumeByApplicantId(int applicantId) {
        ApplicantDto applicant = applicantDtoRepository.findApplicantDtoByApplicantId(applicantId);
        ResumeDto resumeDto;
        if(applicant != null) {
           resumeDto = applicant.getResume();
        } else {
            resumeDto = new ResumeDto();
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
            resumeVo = new ResumeVo.Builder().build();
        }
        return resumeVo;
    }

    @Override
    public int filterApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
        } catch (DataAccessException e) {

        }
        return 0;
    }

    @Override
    public List<InterviewerVo> listInterviewers() {
        return null;
    }

    @Override
    public int createInterview(int applicationId, int interviewerId) {
        return 0;
    }
}
