package com.targaryen.octopus.vo;

import lombok.Getter;

@Getter
public class ResumeModelVo {
    private final int resumeModelId;
    private final int postId;
    private final boolean applicantName;
    private final boolean applicantSex;
    private final boolean applicantAge;
    private final boolean applicantSchool;
    private final boolean applicantDegree;
    private final boolean applicantMajor;
    private final boolean applicantCity;
    private final boolean applicantEmail;
    private final boolean applicantPhone;
    private final boolean applicantCV;
    private final boolean applicantHometown;
    private final boolean applicantNation;
    private final boolean applicantPoliticalStatus;
    private final boolean applicantMaritalStatus;
    private final boolean applicantDateOfBirth;
    private final boolean applicantTimeToWork;
    private final boolean applicantCurrentSalary;
    private final boolean applicantExpectSalary;
    private final boolean applicantDutyTime;
    private final boolean recommenderName;
    private final boolean applicantAddress;
    private final boolean applicantSelfIntro;
    private final boolean applicantPhoto;
    private final boolean applicantDegreePhoto;
    private final boolean familyContactRelation;
    private final boolean familyContactName;
    private final boolean familyContactCompany;
    private final boolean familyContactPhoneNum;

    public static class Builder {
        private int resumeModelId;
        private int postId;
        private boolean applicantName;
        private boolean applicantSex;
        private boolean applicantAge;
        private boolean applicantSchool;
        private boolean applicantDegree;
        private boolean applicantMajor;
        private boolean applicantCity;
        private boolean applicantEmail;
        private boolean applicantPhone;
        private boolean applicantCV;
        private boolean applicantHometown;
        private boolean applicantNation;
        private boolean applicantPoliticalStatus;
        private boolean applicantMaritalStatus;
        private boolean applicantDateOfBirth;
        private boolean applicantTimeToWork;
        private boolean applicantCurrentSalary;
        private boolean applicantExpectSalary;
        private boolean applicantDutyTime;
        private boolean recommenderName;
        private boolean applicantAddress;
        private boolean applicantSelfIntro;
        private boolean applicantPhoto;
        private boolean applicantDegreePhoto;
        private boolean familyContactRelation;
        private boolean familyContactName;
        private boolean familyContactCompany;
        private boolean familyContactPhoneNum;

        public Builder resumeModelId(int resumeModelId) {
            this.resumeModelId = resumeModelId;
            return this;
        }

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder applicantName(boolean applicantName) {
            this.applicantName = applicantName;
            return this;
        }

        public Builder applicantAge(boolean applicantAge) {
            this.applicantAge = applicantAge;
            return this;
        }

        public Builder applicantSex(boolean applicantSex) {
            this.applicantSex = applicantSex;
            return this;
        }

        public Builder applicantSchool(boolean applicantSchool) {
            this.applicantSchool = applicantSchool;
            return this;
        }

        public Builder applicantDegree(boolean applicantDegree) {
            this.applicantDegree = applicantDegree;
            return this;
        }

        public Builder applicantMajor(boolean applicantMajor) {
            this.applicantMajor = applicantMajor;
            return this;
        }

        public Builder applicantCity(boolean applicantCity) {
            this.applicantCity = applicantCity;
            return this;
        }

        public Builder applicantEmail(boolean applicantEmail) {
            this.applicantEmail = applicantEmail;
            return this;
        }

        public Builder applicantPhone(boolean applicantPhone) {
            this.applicantPhone = applicantPhone;
            return this;
        }

        public Builder applicantCV(boolean applicantCV) {
            this.applicantCV = applicantCV;
            return this;
        }

        public Builder applicantHometown(boolean applicantHometown) {
            this.applicantHometown = applicantHometown;
            return this;
        }

        public Builder applicantNation(boolean applicantNation) {
            this.applicantNation = applicantNation;
            return this;
        }

        public Builder applicantPoliticalStatus(boolean applicantPoliticalStatus) {
            this.applicantPoliticalStatus = applicantPoliticalStatus;
            return this;
        }

        public Builder applicantMaritalStatus(boolean applicantMaritalStatus) {
            this.applicantMaritalStatus = applicantMaritalStatus;
            return this;
        }

        public Builder applicantDateOfBirth(boolean applicantDateOfBirth) {
            this.applicantDateOfBirth = applicantDateOfBirth;
            return this;
        }

        public Builder applicantTimeToWork(boolean applicantTimeToWork) {
            this.applicantTimeToWork = applicantTimeToWork;
            return this;
        }

        public Builder applicantCurrentSalary(boolean applicantCurrentSalary) {
            this.applicantCurrentSalary = applicantCurrentSalary;
            return this;
        }

        public Builder applicantExpectSalary(boolean applicantExpectSalary) {
            this.applicantExpectSalary = applicantExpectSalary;
            return this;
        }

        public Builder applicantDutyTime(boolean applicantDutyTime) {
            this.applicantDutyTime = applicantDutyTime;
            return this;
        }

        public Builder recommenderName(boolean recommenderName) {
            this.recommenderName = recommenderName;
            return this;
        }

        public Builder applicantAddress(boolean applicantAddress) {
            this.applicantAddress = applicantAddress;
            return this;
        }

        public Builder applicantSelfIntro(boolean applicantSelfIntro) {
            this.applicantSelfIntro = applicantSelfIntro;
            return this;
        }

        public Builder applicantPhoto(boolean applicantPhoto) {
            this.applicantPhoto = applicantPhoto;
            return this;
        }

        public Builder applicantDegreePhoto(boolean applicantDegreePhoto) {
            this.applicantDegreePhoto = applicantDegreePhoto;
            return this;
        }

        public Builder familyContactRelation(boolean familyContactRelation) {
            this.familyContactRelation = familyContactRelation;
            return this;
        }

        public Builder familyContactName(boolean familyContactName) {
            this.familyContactName = familyContactName;
            return this;
        }

        public Builder familyContactCompany(boolean familyContactCompany) {
            this.familyContactCompany = familyContactCompany;
            return this;
        }

        public Builder familyContactPhoneNum(boolean familyContactPhoneNum) {
            this.familyContactPhoneNum = familyContactPhoneNum;
            return this;
        }

        public ResumeModelVo build() {
            return new ResumeModelVo(this);
        }
    }

    private ResumeModelVo(Builder builder) {
        this.applicantAddress = builder.applicantAddress;
        this.applicantAge = builder.applicantAge;
        this.applicantCity = builder.applicantCity;
        this.applicantCurrentSalary = builder.applicantCurrentSalary;
        this.applicantCV = builder.applicantCV;
        this.applicantDateOfBirth = builder.applicantDateOfBirth;
        this.applicantDegree = builder.applicantDegree;
        this.applicantDegreePhoto = builder.applicantDegreePhoto;
        this.applicantDutyTime = builder.applicantDutyTime;
        this.applicantEmail = builder.applicantEmail;
        this.applicantExpectSalary = builder.applicantExpectSalary;
        this.applicantHometown = builder.applicantHometown;
        this.applicantMajor = builder.applicantMajor;
        this.applicantMaritalStatus = builder.applicantMaritalStatus;
        this.applicantName = builder.applicantName;
        this.applicantNation = builder.applicantNation;
        this.applicantPhone = builder.applicantPhone;
        this.applicantPoliticalStatus = builder.applicantPoliticalStatus;
        this.applicantPhoto = builder.applicantPhoto;
        this.applicantSchool = builder.applicantSchool;
        this.applicantSelfIntro = builder.applicantSelfIntro;
        this.applicantSex = builder.applicantSex;
        this.applicantTimeToWork = builder.applicantTimeToWork;
        this.familyContactCompany = builder.familyContactCompany;
        this.familyContactName = builder.familyContactName;
        this.familyContactPhoneNum = builder.familyContactPhoneNum;
        this.familyContactRelation = builder.familyContactRelation;
        this.recommenderName = builder.recommenderName;
        this.resumeModelId = builder.resumeModelId;
        this.postId = builder.postId;
    }
}
