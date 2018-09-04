package com.targaryen.octopus.vo;

import lombok.Getter;

/**
 * @author He Junfeng
 */
@Getter
public class ApplicationVo {
    private final int applicationId;
    private final int status;
    private final int applicantId;
    private final int postId;

    public static class Builder {
        private int applicationId;
        private int status;
        private int applicantId;
        private int postId;

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

        public ApplicationVo build() {
            return new ApplicationVo(this);
        }
    }

    private ApplicationVo(Builder builder) {
        this.applicationId = builder.applicationId;
        this.status = builder.status;
        this.applicantId = builder.applicantId;
        this.postId = builder.postId;
    }
}
