package com.targaryen.octopus.vo;


import java.util.Date;

public class ApplicantApplicationVo {
    private final int applicationId;
    private final int status;
    private final int applicantId;
    private final int postId;
    private final String applicantName;
    private final String postName;
    private final String postLocale;
    private final String postType;
    private final String recruitDpt;

    public static class Builder {
        private int applicationId;
        private int status;
        private int applicantId;
        private int postId;
        private String applicantName;
        private String postName;
        private String postLocale;
        private String postType;
        private String recruitDpt;

        public Builder applicationId(int applicationId) {
            this.applicantId = applicationId;
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

        public Builder postName(String postName) {
            this.postName = postName;
            return this;
        }

        public Builder postLocale(String postLocale) {
            this.postLocale = postLocale;
            return this;
        }

        public Builder postType(String postType) {
            this.postType = postType;
            return this;
        }

        public Builder recruitDpt(String recruitDpt) {
            this.recruitDpt = recruitDpt;
            return this;
        }

        public ApplicantApplicationVo build() {
            return new ApplicantApplicationVo(this);
        }
    }

    private ApplicantApplicationVo(Builder builder) {
        this.applicationId  = builder.applicationId;
        this.status = builder.status;
        this.applicantId = builder.applicationId;
        this.postId = builder.postId;
        this.applicantName = builder.applicantName;
        this.postName = builder.postName;
        this.postLocale = builder.postLocale;
        this.postType = builder.postType;
        this.recruitDpt = builder.recruitDpt;
    }

}
