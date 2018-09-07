package com.targaryen.octopus.vo;

import lombok.Getter;

/**
 * @author He Junfeng
 */
@Getter
public class InterviewerVo {
    private final int interviewerId;
    private final String interviewerName;
    private final String interviewerPosition;
    private final int interviewerAge;
    private final String interviewerDepartment;
    private final int userId;

    public static class Builder {
        private int interviewerId;
        private String interviewerName;
        private String interviewerPosition;
        private int interviewerAge;
        private String interviewerDepartment;
        private int userId;

        public Builder interviewerId(int interviewerId) {
            this.interviewerId = interviewerId;
            return this;
        }

        public Builder interviewerName(String interviewerName) {
            this.interviewerName = interviewerName;
            return this;
        }

        public Builder interviewPosition(String interviewerPosition) {
            this.interviewerPosition = interviewerPosition;
            return this;
        }

        public Builder interviewAge(int interviewerAge) {
            this.interviewerAge = interviewerAge;
            return this;
        }

        public Builder interviewDepartment(String interviewerDepartment) {
            this.interviewerDepartment = interviewerDepartment;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public InterviewerVo build() {
            return new InterviewerVo(this);
        }
    }

    private InterviewerVo(Builder builder) {
        this.interviewerId = builder.interviewerId;
        this.interviewerName = builder.interviewerName;
        this.interviewerAge = builder.interviewerAge;
        this.interviewerPosition = builder.interviewerPosition;
        this.interviewerDepartment = builder.interviewerDepartment;
        this.userId = builder.userId;
    }
}
