package com.targaryen.octopus.util;

import com.targaryen.octopus.dto.*;
import com.targaryen.octopus.util.status.InterviewResultStatus;
import com.targaryen.octopus.util.status.ReservationStatus;
import com.targaryen.octopus.vo.*;

/**
 * @author He Junfeng
 */
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
                .postId(interviewDto.getPost().getPostId())
                .interviewRound(interviewDto.getInterviewRound())
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
                .departmentId(postDto.getDepartment().getDepartmentId())
                .recruitType(postDto.getRecruitType())
                .interviewRound(postDto.getInterviewRound())
                .build();
    }

    public static PostDto updatePostDtoByVo(PostDto postDto, PostVo postVo) {
        postDto.setPostName(postVo.getPostName());
        postDto.setPostType(postVo.getPostType());
        postDto.setPostLocale(postVo.getPostLocale());
        postDto.setPostDescription(postVo.getPostDescription());
        postDto.setPostRequirement(postVo.getPostRequirement());
        postDto.setRecruitNum(postVo.getRecruitNum());
        postDto.setRecruitDpt(postVo.getRecruitDpt());
        postDto.setRecruitType(postVo.getRecruitType());
        return postDto;
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
                .applicantHometown(resumeDto.getApplicantHometown())
                .applicantNation(resumeDto.getApplicantNation())
                .applicantPoliticalStatus(resumeDto.getApplicantPoliticalStatus())
                .applicantMaritalStatus(resumeDto.getApplicantMaritalStatus())
                .applicantDateOfBirth(resumeDto.getApplicantDateOfBirth())
                .applicantTimeToWork(resumeDto.getApplicantTimeToWork())
                .applicantCurrentSalary(resumeDto.getApplicantCurrentSalary())
                .applicantExpectSalary(resumeDto.getApplicantExpectSalary())
                .applicantDutyTime(resumeDto.getApplicantDutyTime())
                .recommenderName(resumeDto.getRecommenderName())
                .applicantAddress(resumeDto.getApplicantAddress())
                .applicantSelfIntro(resumeDto.getApplicantSelfIntro())
                .applicantPhoto(resumeDto.getApplicantPhoto())
                .applicantDegreePhoto(resumeDto.getApplicantDegreePhoto())
                .familyContactName(resumeDto.getFamilyContactName())
                .familyContactRelation(resumeDto.getFamilyContactRelation())
                .familyContactCompany(resumeDto.getFamilyContactCompany())
                .familyContactPhoneNum(resumeDto.getFamilyContactPhoneNum())
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

    public static ApplicationResumeVo ApplicationDtoToApplicationResumeVo(ApplicationDto applicationDto) {
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
                .applicantHometown(resume.getApplicantHometown())
                .applicantNation(resume.getApplicantNation())
                .applicantPoliticalStatus(resume.getApplicantPoliticalStatus())
                .applicantMaritalStatus(resume.getApplicantMaritalStatus())
                .applicantDateOfBirth(resume.getApplicantDateOfBirth())
                .applicantTimeToWork(resume.getApplicantTimeToWork())
                .applicantCurrentSalary(resume.getApplicantCurrentSalary())
                .applicantExpectSalary(resume.getApplicantExpectSalary())
                .applicantDutyTime(resume.getApplicantDutyTime())
                .recommenderName(resume.getRecommenderName())
                .applicantAddress(resume.getApplicantAddress())
                .applicantSelfIntro(resume.getApplicantSelfIntro())
                .applicantPhoto(resume.getApplicantPhoto())
                .applicantDegreePhoto(resume.getApplicantDegreePhoto())
                .familyContactName(resume.getFamilyContactName())
                .familyContactRelation(resume.getFamilyContactRelation())
                .familyContactCompany(resume.getFamilyContactCompany())
                .familyContactPhoneNum(resume.getFamilyContactPhoneNum())
                .build();
    }

    public static ApplicantApplicationVo ApplicationDtoToApplicantApplicationVo(ApplicationDto applicationDto) {
        PostDto postDto = applicationDto.getPost();
        ApplicantDto applicantDto = applicationDto.getApplicant();
        ResumeDto resumeDto = applicantDto.getResume();
        return new ApplicantApplicationVo.Builder()
                .applicantId(applicantDto.getApplicantId())
                .applicantName(resumeDto.getApplicantName())
                .applicationId(applicationDto.getApplicationId())
                .postId(postDto.getPostId())
                .postLocale(postDto.getPostLocale())
                .postName(postDto.getPostName())
                .postType(postDto.getPostType())
                .recruitDpt(postDto.getRecruitDpt())
                .createTime(applicationDto.getCreateTime())
                .filterEndTime(applicationDto.getFilterEndTime())
                .interviewEndTime(applicationDto.getInterviewEndTime())
                .dptApproveEndTime(applicationDto.getDptApproveEndTime())
                .offerTime(applicationDto.getOfferTime())
                .applicantFeedbackTime(applicationDto.getApplicantFeedbackTime())
                .status(applicationDto.getStatus()).build();
    }

    public static ApplicantInterviewVo InterviewDtoToApplicantInterviewVo(InterviewDto interviewDto) {
        ApplicationDto applicationDto = interviewDto.getApplication();
        PostDto postDto = applicationDto.getPost();
        InterviewerDto interviewerDto = interviewDto.getInterviewer();
        return new ApplicantInterviewVo.Builder()
                .interviewId(interviewDto.getInterviewId())
                .applicationId(applicationDto.getApplicationId())
                .interviewerId(interviewerDto.getInterviewerId())
                .interviewerName(interviewerDto.getInterviewerName())
                .interviewPlace(interviewDto.getInterviewPlace())
                .interviewResultComment(interviewDto.getInterviewResultComment())
                .interviewResultStatus(interviewDto.getInterviewResultStatus())
                .interviewStartTime(interviewDto.getInterviewStartTime())
                .postLocale(postDto.getPostLocale())
                .postName(postDto.getPostName())
                .postType(postDto.getPostType())
                .recruitDpt(postDto.getRecruitDpt()).build();
    }

    public static ResumeModelVo ResumeModeDtoToVo(ResumeModelDto resumeModelDto) {
        return new ResumeModelVo.Builder()
                .applicantAddress(resumeModelDto.isApplicantAddress())
                .applicantAge(resumeModelDto.isApplicantAge())
                .applicantCity(resumeModelDto.isApplicantCity())
                .applicantCurrentSalary(resumeModelDto.isApplicantCurrentSalary())
                .applicantCV(resumeModelDto.isApplicantCV())
                .applicantDateOfBirth(resumeModelDto.isApplicantDateOfBirth())
                .applicantDegree(resumeModelDto.isApplicantDegree())
                .applicantDegreePhoto(resumeModelDto.isApplicantDegreePhoto())
                .applicantDutyTime(resumeModelDto.isApplicantDutyTime())
                .applicantEmail(resumeModelDto.isApplicantEmail())
                .applicantExpectSalary(resumeModelDto.isApplicantExpectSalary())
                .applicantHometown(resumeModelDto.isApplicantHometown())
                .applicantMajor(resumeModelDto.isApplicantMajor())
                .applicantMaritalStatus(resumeModelDto.isApplicantMaritalStatus())
                .applicantName(resumeModelDto.isApplicantName())
                .applicantNation(resumeModelDto.isApplicantNation())
                .applicantPhone(resumeModelDto.isApplicantPhone())
                .applicantPoliticalStatus(resumeModelDto.isApplicantPoliticalStatus())
                .applicantSchool(resumeModelDto.isApplicantSchool())
                .applicantSelfIntro(resumeModelDto.isApplicantSelfIntro())
                .applicantSex(resumeModelDto.isApplicantSex())
                .applicantTimeToWork(resumeModelDto.isApplicantTimeToWork())
                .familyContactCompany(resumeModelDto.isFamilyContactCompany())
                .familyContactName(resumeModelDto.isFamilyContactName())
                .familyContactPhoneNum(resumeModelDto.isFamilyContactPhoneNum())
                .familyContactRelation(resumeModelDto.isFamilyContactRelation())
                .postId(resumeModelDto.getPost().getPostId())
                .recommenderName(resumeModelDto.isRecommenderName())
                .resumeModelId(resumeModelDto.getResumeModelId())
                .applicantPhoto(resumeModelDto.isApplicantPhoto())
                .build();
    }

    public static ResumeModelDto updateResumeModelDtoByVo(ResumeModelDto resumeModelDto, ResumeModelVo resumeModelVo) {
        resumeModelDto.setApplicantAddress(resumeModelVo.isApplicantAddress());
        resumeModelDto.setApplicantAge(resumeModelVo.isApplicantAge());
        resumeModelDto.setApplicantCity(resumeModelVo.isApplicantCity());
        resumeModelDto.setApplicantCurrentSalary(resumeModelVo.isApplicantCurrentSalary());
        resumeModelDto.setApplicantCV(resumeModelVo.isApplicantCV());
        resumeModelDto.setApplicantDateOfBirth(resumeModelVo.isApplicantDateOfBirth());
        resumeModelDto.setApplicantDegree(resumeModelVo.isApplicantDegree());
        resumeModelDto.setApplicantDegreePhoto(resumeModelVo.isApplicantDegree());
        resumeModelDto.setApplicantDutyTime(resumeModelVo.isApplicantDutyTime());
        resumeModelDto.setApplicantEmail(resumeModelVo.isApplicantEmail());
        resumeModelDto.setApplicantExpectSalary(resumeModelVo.isApplicantExpectSalary());
        resumeModelDto.setApplicantHometown(resumeModelVo.isApplicantHometown());
        resumeModelDto.setApplicantMajor(resumeModelVo.isApplicantMajor());
        resumeModelDto.setApplicantMaritalStatus(resumeModelVo.isApplicantMaritalStatus());
        resumeModelDto.setApplicantName(resumeModelVo.isApplicantName());
        resumeModelDto.setApplicantNation(resumeModelVo.isApplicantNation());
        resumeModelDto.setApplicantPhone(resumeModelVo.isApplicantPhone());
        resumeModelDto.setApplicantPhoto(resumeModelVo.isApplicantPhoto());
        resumeModelDto.setApplicantPoliticalStatus(resumeModelVo.isApplicantPoliticalStatus());
        resumeModelDto.setApplicantSchool(resumeModelVo.isApplicantSchool());
        resumeModelDto.setApplicantSelfIntro(resumeModelVo.isApplicantSelfIntro());
        resumeModelDto.setApplicantSex(resumeModelVo.isApplicantSex());
        resumeModelDto.setApplicantTimeToWork(resumeModelVo.isApplicantTimeToWork());
        resumeModelDto.setFamilyContactCompany(resumeModelVo.isFamilyContactCompany());
        resumeModelDto.setFamilyContactName(resumeModelVo.isFamilyContactName());
        resumeModelDto.setFamilyContactPhoneNum(resumeModelVo.isFamilyContactPhoneNum());
        resumeModelDto.setFamilyContactRelation(resumeModelVo.isFamilyContactRelation());
        resumeModelDto.setRecommenderName(resumeModelVo.isRecommenderName());
        return resumeModelDto;
    }

    public static InterviewerInterviewVo InterviewDtoToInterviewerInterviewVo(InterviewDto interviewDto) {
        ApplicationDto applicationDto = interviewDto.getApplication();
        PostDto postDto = applicationDto.getPost();
        InterviewerDto interviewerDto = interviewDto.getInterviewer();
        ApplicantDto applicantDto = applicationDto.getApplicant();
        ResumeDto resumeDto = applicantDto.getResume();
        int rounds = (int)applicationDto.getInterviews()
                .stream().filter(x -> x.getReservationStatus() >= 0)
                .filter(x -> x.getCreateTime().before(interviewDto.getCreateTime()))
                .count() + 1;
        return new InterviewerInterviewVo.Builder()
                .interviewId(interviewDto.getInterviewId())
                .applicationId(applicationDto.getApplicationId())
                .applicantId(applicantDto.getApplicantId())
                .applicantName(resumeDto.getApplicantName())
                .rounds(rounds)
                .interviewerId(interviewerDto.getInterviewerId())
                .interviewerName(interviewerDto.getInterviewerName())
                .interviewerStatus(interviewDto.getInterviewerStatus())
                .applicantStatus(interviewDto.getApplicantStatus())
                .reservationStatus(interviewDto.getReservationStatus())
                .interviewPlace(interviewDto.getInterviewPlace())
                .interviewResultComment(interviewDto.getInterviewResultComment())
                .interviewResultStatus(interviewDto.getInterviewResultStatus())
                .interviewStartTime(interviewDto.getInterviewStartTime())
                .postLocale(postDto.getPostLocale())
                .postName(postDto.getPostName())
                .postType(postDto.getPostType())
                .recruitDpt(postDto.getRecruitDpt()).build();
    }

    public static DepartmentVo DepartmentDtoToVo(DepartmentDto departmentDto) {
        return new DepartmentVo.Builder()
                .departmentId(departmentDto.getDepartmentId())
                .departmentName(departmentDto.getDepartmentName())
                .build();
    }
}
