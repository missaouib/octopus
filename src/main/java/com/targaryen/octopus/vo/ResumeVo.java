package com.targaryen.octopus.vo;

import lombok.Getter;

/**
 * @author He Junfeng
 */
@Getter
public class ResumeVo {
    private final int resumeId;
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
        private int resumeId;
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

        public Builder resumeId(int resumeId) {
            this.resumeId = resumeId;
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

        public ResumeVo build() {
            return new ResumeVo(this);
        }
    }

    private ResumeVo(Builder builder) {
        this.resumeId = builder.resumeId;
        this.applicantName = builder.applicantName;
        this.applicantSex = builder.applicantSex;
        this.applicantAge = builder.applicantAge;
        this.applicantSchool = builder.applicantSchool;
        this.applicantDegree = builder.applicantDegree;
        this.applicantMajor = builder.applicantMajor;
        this.applicantCity = builder.applicantCity;
        this.applicantEmail = builder.applicantEmail;
        this.applicantPhone = builder.applicantPhone;
        this.applicantCV = builder.applicantCV;
    }
}
