package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.*;
import com.targaryen.octopus.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private InterviewerDtoRepository interviewerDtoRepository;
    private InterviewDtoRepository interviewDtoRepository;

    @Autowired
    public HRServiceImpl(DaoFactory daoFactory) {
        this.applicantDtoRepository = daoFactory.getApplicantDtoRepository();
        this.postDtoRepository = daoFactory.getPostDtoRepository();
        this.applicationDtoRepository = daoFactory.getApplicationDtoRepository();
        this.interviewerDtoRepository = daoFactory.getInterviewerDtoRepository();
        this.interviewDtoRepository = daoFactory.getInterviewDtoRepository();
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
    public int filterApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            application.setStatus(ApplicationStatus.FILTED);
            applicationDtoRepository.save(application);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public int revokeFilterApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            application.setStatus(ApplicationStatus.INIT);
            applicationDtoRepository.save(application);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public List<InterviewerVo> listInterviewers() {
        return interviewerDtoRepository.findAll().stream()
                .map(n -> new InterviewerVo.Builder()
                        .interviewerId(n.getInterviewerId())
                        .interviewerName(n.getInterviewerName())
                        .interviewAge(n.getInterviewerAge())
                        .interviewDepartment(n.getInterviewerDepartment())
                        .interviewPosition(n.getInterviewerPosition())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public int createInterview(int applicationId, int interviewerId, Date startTime, String interviewPlace) {
        try {
            InterviewDto newInterview = new InterviewDto();
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            InterviewerDto interviewer = interviewerDtoRepository.findInterviewerDtoByInterviewerId(interviewerId);
            newInterview.setApplication(application);
            newInterview.setInterviewer(interviewer);
            newInterview.setStartTime(startTime);
            newInterview.setInterviewPlace(interviewPlace);
            newInterview.setApplicantStatus(ApplicantStatus.INIT);
            newInterview.setInterviewerStatus(InterviewerStatus.INIT);
            newInterview.setInterviewStatus(InterviewStatus.INIT);
            newInterview.setInterviewResultStatus(InterviewResultStatus.INIT);
            interviewDtoRepository.save(newInterview);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public List<InterviewVo> findInterviewByApplicationId(int applicationId) {
        ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
        if(application != null) {
            return application.getInterviews().stream()
                    .map(n -> new InterviewVo.Builder()
                            .interviewId(n.getInterviewId())
                            .startTime(n.getStartTime())
                            .interviewPlace(n.getInterviewPlace())
                            .applicantStatus(n.getApplicantStatus())
                            .applicantComment(n.getApplicantComment())
                            .interviewerStatus(n.getInterviewerStatus())
                            .interviewerComment(n.getInterviewerComment())
                            .interviewStatus(n.getInterviewStatus())
                            .interviewResultStatus(n.getInterviewResultStatus())
                            .interviewResultComment(n.getInterviewResultComment())
                            .applicationId(n.getApplication().getApplicationId())
                            .interviewerId(n.getInterviewer().getInterviewerId())
                            .build())
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
