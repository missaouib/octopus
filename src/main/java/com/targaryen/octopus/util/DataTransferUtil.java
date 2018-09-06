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
                .interviewStartTime(interviewDto.getInterviewStartTime())
                .interviewPlace(interviewDto.getInterviewPlace())
                .applicationId(interviewDto.getApplication().getApplicationId())
                .interviewerId(interviewDto.getInterviewer().getInterviewerId())
                .reservationStatus(interviewDto.getReservationStatus())
                .applicantStatus(interviewDto.getApplicantStatus())
                .applicantComment(interviewDto.getApplicantComment())
                .interviewerStatus(interviewDto.getInterviewerStatus())
                .interviewerComment(interviewDto.getInterviewerComment())
                .interviewResultStatus(interviewDto.getInterviewResultStatus())
                .interviewResultComment(interviewDto.getInterviewResultComment())
                .createTime(interviewDto.getCreateTime())
                .interviewResultTime(interviewDto.getInterviewResultTime())
                .reservationResultTime(interviewDto.getReservationResultTime())
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

<<<<<<< HEAD
    public static ApplicationResumeVo ApplicationDtoToApplicationResumeVo(ApplicationDto applicationDto) {
=======
    public static ApplicationResumeVo ApplicationDtoToResumeVo(ApplicationDto applicationDto) {
>>>>>>> 9ae128e1b55fecbe08808d714de843eb77a5905c
        PostDto post = applicationDto.getPost();
        ApplicantDto applicant = applicationDto.getApplicant();
        ResumeDto resume = applicant.getResume();
        return new ApplicationResumeVo.Builder()
                .applicationId(applicationDto.getApplicationId())
                .postId(post.getPostId())
                .applicantId(applicant.getApplicantId())
                .status(applicationDto.getStatus())
                .applicantAge(resume.getApplicantAge())
                .applicantCity(resume.getApplicantCity())
                .applicantCV(resume.getApplicantCV())
                .applicantDegree(resume.getApplicantDegree())
                .applicantEmail(resume.getApplicantEmail())
                .applicantMajor(resume.getApplicantMajor())
                .applicantName(resume.getApplicantName())
                .applicantPhone(resume.getApplicantPhone())
                .applicantSchool(resume.getApplicantSchool())
                .applicantSex(resume.getApplicantSex())
                .createTime(applicationDto.getCreateTime())
                .filterEndTime(applicationDto.getFilterEndTime())
                .interviewEndTime(applicationDto.getInterviewEndTime())
                .dptApproveEndTime(applicationDto.getDptApproveEndTime())
                .offerTime(applicationDto.getOfferTime())
                .applicantFeedbackTime(applicationDto.getApplicantFeedbackTime())
                .build();
    }
}
