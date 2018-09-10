package com.targaryen.octopus.vo;

import java.util.Date;

public class InterviewerInterviewVo {
    private final int interviewId;
    private final Date interviewStartTime;
    private final String interviewPlace;
    private final int interviewResultStatus;
    private final String interviewResultComment;
    private final int applicationId;
    private final int interviewerId;
    private final String interviewerName;
    private final int interviewerStatus;
    private final int applicantId;
    private final String applicantName;
    private final int rounds;
    private final String postName;
    private final String recruitDpt;
    private final String postType;
    private final String postLocale;

    public static class Builder {
        private int interviewId;
        private Date interviewStartTime;
        private String interviewPlace;
        private int interviewResultStatus;
        private String interviewResultComment;
        private int applicationId;
        private int interviewerId;
        private String interviewerName;
        private int interviewerStatus;
        private int applicantId;
        private String applicantName;
        private int rounds;
        private String postName;
        private String recruitDpt;
        private String postType;
        private String postLocale;


        public Builder interviewId(int interviewId) {
            this.interviewId = interviewId;
            return this;
        }

        public Builder interviewStartTime(Date interviewStartTime) {
            this.interviewStartTime = interviewStartTime;
            return this;
        }

        public Builder interviewPlace(String interviewPlace) {
            this.interviewPlace = interviewPlace;
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

        public Builder interviewerName(String interviewerName) {
            this.interviewerName = interviewerName;
            return this;
        }

        public Builder interviewerStatus(int interviewerStatus) {
            this.interviewerStatus = interviewerStatus;
            return this;
        }

        public Builder applicantId(int applicantId) {
            this.applicantId = applicantId;
            return this;
        }

        public Builder applicantName(String applicantName) {
            this.applicantName = applicantName;
            return this;
        }

        public Builder rounds(int rounds) {
            this.rounds = rounds;
            return this;
        }

        public Builder postName(String postName) {
            this.postName = postName;
            return this;
        }

        public Builder recruitDpt(String recruitDpt) {
            this.recruitDpt = recruitDpt;
            return this;
        }

        public Builder postType(String postType) {
            this.postType = postType;
            return this;
        }

        public Builder postLocale(String postLocale) {
            this.postLocale = postLocale;
            return this;
        }


        public InterviewerInterviewVo build() {
            return new InterviewerInterviewVo(this);
        }

    }

    private InterviewerInterviewVo(Builder builder) {
        this.interviewId = builder.interviewId;
        this.interviewStartTime = builder.interviewStartTime;
        this.interviewPlace = builder.interviewPlace;
        this.interviewResultStatus = builder.interviewResultStatus;
        this.interviewResultComment = builder.interviewResultComment;
        this.applicationId = builder.applicationId;
        this.interviewerId = builder.interviewerId;
        this.interviewerName = builder.interviewerName;
        this.interviewerStatus = builder.interviewerStatus;
        this.applicantId = builder.applicantId;
        this.applicantName = builder.applicantName;
        this.rounds = builder.rounds;
        this.postName = builder.postName;
        this.recruitDpt = builder.recruitDpt;
        this.postType = builder.postType;
        this.postLocale = builder.postLocale;
    }
}
