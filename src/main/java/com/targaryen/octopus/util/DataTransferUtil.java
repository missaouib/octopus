package com.targaryen.octopus.util;

import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.vo.*;

public class DataTransferUtil {

    public static ApplicationVo ApplicationDtoToVo(ApplicationDto applicationDto) {
        return new ApplicationVo.Builder()
                .applicationId(applicationDto.getApplicationId())
                .status(applicationDto.getStatus())
                .applicantId(applicationDto.getApplicant().getApplicantId())
                .postId(applicationDto.getPost().getPostId()).build();
    }

    public static InterviewVo InterviewDtoToVo(InterviewDto interviewDto) {
        return new InterviewVo.Builder()
                .interviewId(interviewDto.getInterviewId())
                .startTime(interviewDto.getStartTime())
                .interviewPlace(interviewDto.getInterviewPlace())
                .applicationId(interviewDto.getApplication().getApplicationId())
                .interviewerId(interviewDto.getInterviewer().getInterviewerId())
                .interviewStatus(interviewDto.getInterviewStatus())
                .applicantStatus(interviewDto.getApplicantStatus())
                .applicantComment(interviewDto.getApplicantComment())
                .interviewerStatus(interviewDto.getInterviewerStatus())
                .interviewerComment(interviewDto.getInterviewerComment())
                .interviewResultStatus(interviewDto.getInterviewResultStatus())
                .interviewResultComment(interviewDto.getInterviewResultComment())
                .build();
    }

    public static InterviewerVo InterviewerDtoToVo(InterviewerDto interviewerDto) {
        return new InterviewerVo.Builder()
                .interviewerId(interviewerDto.getInterviewerId())
                .interviewPosition(interviewerDto.getInterviewerPosition())
                .interviewDepartment(interviewerDto.getInterviewerDepartment())
                .interviewerName(interviewerDto.getInterviewerName())
                .interviewAge(interviewerDto.getInterviewerAge())
                .userId(interviewerDto.getUser().getUserId())
                .build();
    }

    public static PostVo PostDtoToVo(PostDto postDto) {
        return new PostVo.Builder()
                .postId(postDto.getPostId())
                .postName(postDto.getPostName())
                .postType(postDto.getPostType())
                .postLocale(postDto.getPostLocale())
                .postDescription(postDto.getPostDescription())
                .postRequirement(postDto.getPostRequirement())
                .recruitDpt(postDto.getRecruitDpt())
                .recruitNum(postDto.getRecruitNum())
                .publishTime(postDto.getPublishTime())
                .status(postDto.getStatus())
                .dptManagerId(postDto.getDptManager().getDptManagerId())
                .build();
    }

    public static ResumeVo ResumeDtoToVo(ResumeDto resumeDto) {
        return new ResumeVo.Builder()
                .resumeId(resumeDto.getResumeId())
                .applicantName(resumeDto.getApplicantName())
                .applicantAge(resumeDto.getApplicantAge())
                .applicantCity(resumeDto.getApplicantCity())
                .applicantDegree(resumeDto.getApplicantDegree())
                .applicantEmail(resumeDto.getApplicantEmail())
                .applicantMajor(resumeDto.getApplicantMajor())
                .applicantPhone(resumeDto.getApplicantPhone())
                .applicantSchool(resumeDto.getApplicantSchool())
                .applicantSex(resumeDto.getApplicantSex())
                .applicantCV(resumeDto.getApplicantCV())
                .applicantId(resumeDto.getApplicant().getApplicantId())
                .build();
    }

    public static UserVo UserDtoToVo(UserDto userDto) {
        return new UserVo.Builder()
                .userId(userDto.getUserId())
                .userName(userDto.getUserName())
                .userPassword(userDto.getUserPassword())
                .userRole(userDto.getRole().getRole())
                .build();
    }
}
