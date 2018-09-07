package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.Date;

/**
 * @author He Junfeng
 */
@Getter
public class ApplicationVo {
    private final int applicationId;
    private final int status;
    private final int applicantId;
    private final int postId;
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

        public ApplicationVo build() {
            return new ApplicationVo(this);
        }
    }

    private ApplicationVo(Builder builder) {
        this.applicationId = builder.applicationId;
        this.status = builder.status;
        this.applicantId = builder.applicantId;
        this.postId = builder.postId;
        this.createTime = builder.createTime;
        this.filterEndTime = builder.filterEndTime;
        this.interviewEndTime = builder.interviewEndTime;
        this.dptApproveEndTime = builder.dptApproveEndTime;
        this.offerTime = builder.offerTime;
        this.applicantFeedbackTime = builder.applicantFeedbackTime;
    }
}
