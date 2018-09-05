package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class InterviewVo {
    private final int interviewId;
    private final Date startTime;
    private final String interviewPlace;
    private final int applicantStatus;
    private final String applicantComment;
    private final int interviewerStatus;
    private final String interviewerComment;
    private final int interviewStatus;
    private final int interviewResultStatus;
    private final String interviewResultComment;
    private final int applicationId;
    private final int interviewerId;

    public static class Builder {
        private int interviewId;
        private Date startTime;
        private String interviewPlace;
        private int applicantStatus;
        private String applicantComment;
        private int interviewerStatus;
        private String interviewerComment;
        private int interviewStatus;
        private int interviewResultStatus;
        private String interviewResultComment;
        private int applicationId;
        private int interviewerId;

        public Builder interviewId(int interviewId) {
            this.interviewId = interviewId;
            return this;
        }

        public Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder interviewPlace(String interviewPlace) {
            this.interviewPlace = interviewPlace;
            return this;
        }

        public Builder applicantStatus(int applicantStatus) {
            this.applicantStatus = interviewerStatus;
            return this;
        }

        public Builder applicantComment(String applicantComment) {
            this.applicantComment = applicantComment;
            return this;
        }

        public Builder interviewerStatus(int interviewerStatus) {
            this.interviewerStatus = interviewerStatus;
            return this;
        }

        public Builder interviewerComment(String interviewerComment) {
            this.interviewerComment = interviewerComment;
            return this;
        }

        public Builder interviewStatus(int interviewStatus) {
            this.interviewStatus = interviewStatus;
            return this;
        }

        public Builder interviewResultStatus(int interviewResultStatus) {
            this.interviewResultStatus = interviewResultStatus;
            return this;
        }

        public Builder interviewResultComment(String interviewResultComment) {
            this.interviewResultComment = interviewResultComment;
            return this;
        }

        public Builder applicationId(int applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder interviewerId(int interviewerId) {
            this.interviewerId = interviewerId;
            return this;
        }

        public InterviewVo build() {
            return new InterviewVo(this);
        }

    }

    private InterviewVo(Builder builder) {
        this.interviewId = builder.interviewId;
        this.startTime = builder.startTime;
        this.interviewPlace = builder.interviewPlace;
        this.applicantStatus = builder.applicantStatus;
        this.applicantComment = builder.applicantComment;
        this.interviewerStatus = builder.interviewerStatus;
        this.interviewerComment = builder.interviewerComment;
        this.interviewStatus = builder.interviewStatus;
        this.interviewResultStatus = builder.interviewResultStatus;
        this.interviewResultComment = builder.interviewResultComment;
        this.applicationId = builder.applicationId;
        this.interviewerId = builder.interviewerId;
    }
}
