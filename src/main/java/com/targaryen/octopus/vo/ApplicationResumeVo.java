package com.targaryen.octopus.vo;

import lombok.Getter;

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
    }
}
