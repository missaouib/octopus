package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.*;
import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.DataTransferUtil;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.util.status.*;
import com.targaryen.octopus.vo.*;
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
        return postDtoRepository.findAllByStatusNotOrderByPostIdDesc(PostStatus.REVOKED).stream()
                .map(n -> DataTransferUtil.PostDtoToVo(n))
                .collect(Collectors.toList());
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
    public int finishPostById(int postId) {
        try {
            PostDto postDto = postDtoRepository.findPostDtoByPostId(postId);
            postDto.setStatus(PostStatus.FINISHED);
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
    public List<ApplicationResumeVo> findApplicationsByPostId(int postId) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        if(post != null) {
            return post.getApplications().stream()
                    .map(n -> DataTransferUtil.ApplicationDtoToHRVo(n))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<ApplicationResumeVo>();
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
            resumeVo = DataTransferUtil.ResumeDtoToVo(resumeDto);
        } else {
            resumeVo = null;
        }
        return resumeVo;
    }

    @Override
    public int filterPassApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            application.setStatus(ApplicationStatus.FILTER_PASS);
            applicationDtoRepository.save(application);
            return StatusCode.SUCCESS;
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
    }

    @Override
    public int filterFailApplicationById(int applicationId) {
        try {
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(applicationId);
            application.setStatus(ApplicationStatus.FILTER_FAIL);
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
    public List<ApplicationResumeVo> findApplicationsByPostIdAndStatus(int postId, Integer status) {
        PostDto post = postDtoRepository.findPostDtoByPostId(postId);
        if(post != null) {
            return post.getApplications().stream()
                    .filter(n -> status.equals(n.getStatus()))
                    .map(n -> DataTransferUtil.ApplicationDtoToHRVo(n))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<ApplicationResumeVo>();
        }
    }

    @Override
    public List<InterviewerVo> listInterviewers() {
        return interviewerDtoRepository.findAll().stream()
                .map(n -> DataTransferUtil.InterviewerDtoToVo(n))
                .collect(Collectors.toList());
    }

    @Override
    public int createInterview(InterviewVo interviewVo) {
        try {
            InterviewDto newInterview = new InterviewDto();
            ApplicationDto application = applicationDtoRepository.findApplicationDtoByApplicationId(interviewVo.getApplicationId());
            InterviewerDto interviewer = interviewerDtoRepository.findInterviewerDtoByInterviewerId(interviewVo.getInterviewerId());
            newInterview.setApplication(application);
            newInterview.setInterviewer(interviewer);
            newInterview.setStartTime(interviewVo.getStartTime());
            newInterview.setInterviewPlace(interviewVo.getInterviewPlace());
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
    public InterviewVo findInterviewById(int interviewId) {
        try {
            InterviewDto interviewDto = interviewDtoRepository.findInterviewDtoByInterviewId(interviewId);
            return new InterviewVo.Builder()
                    .interviewId(interviewDto.getInterviewId())
                    .startTime(interviewDto.getStartTime())
                    .interviewPlace(interviewDto.getInterviewPlace())
                    .applicantStatus(interviewDto.getApplicantStatus())
                    .applicantComment(interviewDto.getApplicantComment())
                    .interviewerStatus(interviewDto.getInterviewerStatus())
                    .interviewerComment(interviewDto.getInterviewerComment())
                    .interviewStatus(interviewDto.getInterviewStatus())
                    .interviewResultStatus(interviewDto.getInterviewResultStatus())
                    .interviewResultComment(interviewDto.getInterviewResultComment())
                    .applicationId(interviewDto.getApplication().getApplicationId())
                    .interviewerId(interviewDto.getInterviewer().getInterviewerId())
                    .build();
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteInterviewById(int interviewId) {
        try {
            interviewDtoRepository.deleteInterviewDtoByInterviewId(interviewId);
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
                    .map(n -> DataTransferUtil.InterviewDtoToVo(n))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<InterviewVo>();
        }
    }
}
