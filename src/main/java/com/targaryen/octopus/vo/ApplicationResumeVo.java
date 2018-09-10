package com.targaryen.octopus.vo;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * @author He Junfeng
 */
@Getter
public class ApplicationResumeVo {

    private final int applicationId;
    private final int status;
    private final int applicantId;
    private final int postId;
    private final String applicantName;
    private final int applicantSex;
    private final int applicantAge;
    private final String applicantSchool;
    private final int applicantDegree;
    private final String applicantMajor;
    private final String applicantCity;
    private final String applicantEmail;
    private final String applicantPhone;
    private final String applicantCV;
    private final String applicantHometown;
    private final String applicantNation;
    private final String applicantPoliticalStatus;
    private final String applicantMaritalStatus;
    private final Date applicantDateOfBirth;
    private final Date applicantTimeToWork;
    private final int applicantCurrentSalary;
    private final int applicantExpectSalary;
    private final Date applicantDutyTime;
    private final String recommenderName;
    private final String applicantAddress;
    private final String applicantSelfIntro;
    private final String applicantPhoto;
    private final String applicantDegreePhoto;
    private final String familyContactRelation;
    private final String familyContactName;
    private final String familyContactCompany;
    private final String familyContactPhoneNum;
    private final Date createTime;
    private final Date filterEndTime;
    private final Date interviewEndTime;
    private final Date dptApproveEndTime;
    private final Date offerTime;
    private final Date applicantFeedbackTime;

    public static class Builder {
        private int applicationId;
        private int status;
        private int applicantId;
        private int postId;
        private String applicantName;
        private int applicantSex;
        private int applicantAge;
        private String applicantSchool;
        private int applicantDegree;
        private String applicantMajor;
        private String applicantCity;
        private String applicantEmail;
        private String applicantPhone;
        private String applicantCV;
        private String applicantHometown;
        private String applicantNation;
        private String applicantPoliticalStatus;
        private String applicantMaritalStatus;
        private Date applicantDateOfBirth;
        private Date applicantTimeToWork;
        private int applicantCurrentSalary;
        private int applicantExpectSalary;
        private Date applicantDutyTime;
        private String recommenderName;
        private String applicantAddress;
        private String applicantSelfIntro;
        private String applicantPhoto;
        private String applicantDegreePhoto;
        private String familyContactRelation;
        private String familyContactName;
        private String familyContactCompany;
        private String familyContactPhoneNum;
        private Date createTime;
        private Date filterEndTime;
        private Date interviewEndTime;
        private Date dptApproveEndTime;
        private Date offerTime;
        private Date applicantFeedbackTime;


        public Builder applicationId(int applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder applicantId(int applicantId) {
            this.applicantId = applicantId;
            return this;
        }

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder applicantName(String applicantName) {
            this.applicantName = applicantName;
            return this;
        }

        public Builder applicantSex(int applicantSex) {
            this.applicantSex = applicantSex;
            return this;
        }

        public Builder applicantAge(int applicantAge) {
            this.applicantAge = applicantAge;
            return this;
        }

        public Builder applicantSchool(String applicantSchool) {
            this.applicantSchool = applicantSchool;
            return this;
        }

        public Builder applicantDegree(int applicantDegree) {
            this.applicantDegree = applicantDegree;
            return this;
        }

        public Builder applicantMajor(String applicantMajor) {
            this.applicantMajor = applicantMajor;
            return this;
        }

        public Builder applicantCity(String applicantCity) {
            this.applicantCity = applicantCity;
            return this;
        }

        public Builder applicantEmail(String applicantEmail) {
            this.applicantEmail = applicantEmail;
            return this;
        }

        public Builder applicantPhone(String applicantPhone) {
            this.applicantPhone = applicantPhone;
            return this;
        }

        public Builder applicantCV(String applicantCV) {
            this.applicantCV = applicantCV;
            return this;
        }

        public Builder applicantHometown(String applicantHometown) {
            this.applicantHometown = applicantHometown;
            return this;
        }

        public Builder applicantNation(String applicantNation) {
            this.applicantNation = applicantNation;
            return this;
        }

        public Builder applicantPoliticalStatus(String applicantPoliticalStatus) {
            this.applicantPoliticalStatus = applicantPoliticalStatus;
            return this;
        }

        public Builder applicantMaritalStatus(String applicantMaritalStatus) {
            this.applicantMaritalStatus = applicantMaritalStatus;
            return this;
        }

        public Builder applicantDateOfBirth(Date applicantDateOfBirth) {
            this.applicantDateOfBirth = applicantDateOfBirth;
            return this;
        }

        public Builder applicantTimeToWork(Date applicantTimeToWork) {
            this.applicantTimeToWork = applicantTimeToWork;
            return this;
        }

        public Builder applicantCurrentSalary(int applicantCurrentSalary) {
            this.applicantCurrentSalary = applicantCurrentSalary;
            return this;
        }

        public Builder applicantExpectSalary(int applicantExpectSalary) {
            this.applicantExpectSalary = applicantExpectSalary;
            return this;
        }

        public Builder applicantDutyTime(Date applicantDutyTime) {
            this.applicantDutyTime = applicantDutyTime;
            return this;
        }

        public Builder recommenderName(String recommenderName) {
            this.recommenderName = recommenderName;
            return this;
        }

        public Builder applicantAddress(String applicantAddress) {
            this.applicantAddress = applicantAddress;
            return this;
        }

        public Builder applicantSelfIntro(String applicantSelfIntro) {
            this.applicantSelfIntro = applicantSelfIntro;
            return this;
        }

        public Builder applicantPhoto(String applicantPhoto) {
            this.applicantPhoto = applicantPhoto;
            return this;
        }

        public Builder applicantDegreePhoto(String applicantDegreePhoto) {
            this.applicantDegreePhoto = applicantDegreePhoto;
            return this;
        }

        public Builder familyContactRelation(String familyContactRelation) {
            this.familyContactRelation = familyContactRelation;
            return this;
        }

        public Builder familyContactName(String familyContactName) {
            this.familyContactName = familyContactName;
            return this;
        }

        public Builder familyContactCompany(String familyContactCompany) {
            this.familyContactCompany = familyContactCompany;
            return this;
        }

        public Builder familyContactPhoneNum(String familyContactPhoneNum) {
            this.familyContactPhoneNum = familyContactPhoneNum;
            return this;
        }

        public Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder filterEndTime(Date filterEndTime) {
            this.filterEndTime = filterEndTime;
            return this;
        }

        public Builder interviewEndTime(Date interviewEndTime) {
            this.interviewEndTime = interviewEndTime;
            return this;
        }

        public Builder dptApproveEndTime(Date dptApproveEndTime) {
            this.dptApproveEndTime = dptApproveEndTime;
            return this;
        }

        public Builder offerTime(Date offerTime) {
            this.offerTime = offerTime;
            return this;
        }

        public Builder applicantFeedbackTime(Date applicantFeedbackTime) {
            this.applicantFeedbackTime = applicantFeedbackTime;
            return this;
        }

        public ApplicationResumeVo build() {
            return new ApplicationResumeVo(this);
        }
    }

    private ApplicationResumeVo(Builder builder) {
        this.applicationId = builder.applicationId;
        this.status = builder.status;
        this.applicantId = builder.applicantId;
        this.postId = builder.postId;
        this.applicantName = builder.applicantName;
        this.applicantAge = builder.applicantAge;
        this.applicantCity = builder.applicantCity;
        this.applicantDegree = builder.applicantDegree;
        this.applicantSchool = builder.applicantSchool;
        this.applicantSex = builder.applicantSex;
        this.applicantMajor = builder.applicantMajor;
        this.applicantPhone = builder.applicantPhone;
        this.applicantEmail = builder.applicantEmail;
        this.applicantCV = builder.applicantCV;
        this.createTime = builder.createTime;
        this.filterEndTime = builder.filterEndTime;
        this.interviewEndTime = builder.interviewEndTime;
        this.dptApproveEndTime = builder.dptApproveEndTime;
        this.offerTime = builder.offerTime;
        this.applicantFeedbackTime = builder.applicantFeedbackTime;
        this.applicantHometown = builder.applicantHometown;
        this.applicantNation= builder.applicantNation;
        this.applicantPoliticalStatus = builder.applicantPoliticalStatus;
        this.applicantMaritalStatus = builder.applicantMaritalStatus;
        this.applicantDateOfBirth = builder.applicantDateOfBirth;
        this.applicantTimeToWork = builder.applicantTimeToWork;
        this.applicantCurrentSalary = builder.applicantCurrentSalary;
        this.applicantExpectSalary = builder.applicantExpectSalary;
        this.applicantDutyTime = builder.applicantDutyTime;
        this.recommenderName = builder.recommenderName;
        this.applicantAddress = builder.applicantAddress;
        this.applicantSelfIntro = builder.applicantSelfIntro;
        this.applicantPhoto= builder.applicantPhoto;
        this.applicantDegreePhoto = builder.applicantDegreePhoto;
        this.familyContactRelation= builder.familyContactRelation;
        this.familyContactName = builder.familyContactName;
        this.familyContactCompany = builder.familyContactCompany;
        this.familyContactPhoneNum = builder.familyContactPhoneNum;
    }
}
