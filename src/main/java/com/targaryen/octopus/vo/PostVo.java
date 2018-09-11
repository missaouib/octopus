package com.targaryen.octopus.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author He Junfeng
 */
@Data
public class PostVo {
    private final int postId;
    private final String postName;
    private final String postType;
    private final String postLocale;
    private final String postDescription;
    private final String postRequirement;
    private final int recruitNum;
    private final String recruitDpt;
    private final Date publishTime;
    private final int status;
    private final int departmentId;
    private final int recruitType;
    private final int interviewRound;

    public static class Builder {
        private int postId;
        private String postName;
        private String postType;
        private String postLocale;
        private String postDescription;
        private String postRequirement;
        private int recruitNum;
        private String recruitDpt;
        private Date publishTime;
        private int status;
        private int departmentId;
        private int recruitType;
        private int interviewRound;

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder postName(String postName) {
            this.postName = postName;
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

        public Builder postDescription(String postDescription) {
            this.postDescription = postDescription;
            return this;
        }

        public Builder postRequirement(String requirement) {
            this.postRequirement = requirement;
            return this;
        }

        public Builder recruitNum(int recruitNum) {
            this.recruitNum = recruitNum;
            return this;
        }

        public Builder recruitDpt(String recruitDpt) {
            this.recruitDpt = recruitDpt;
            return this;
        }

        public Builder publishTime(Date publishTime) {
            this.publishTime = publishTime;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder departmentId(int departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public Builder recruitType(int recruitType) {
            this.recruitType = recruitType;
            return this;
        }

        public Builder interviewRound(int interviewRound) {
            this.interviewRound = interviewRound;
            return this;
        }

        public PostVo build() {
            return new PostVo(this);
        }
    }

    private PostVo(Builder builder) {
        this.postId = builder.postId;
        this.postName = builder.postName;
        this.postType = builder.postType;
        this.postLocale = builder.postLocale;
        this.postDescription = builder.postDescription;
        this.postRequirement = builder.postRequirement;
        this.recruitNum = builder.recruitNum;
        this.recruitDpt = builder.recruitDpt;
        this.publishTime = builder.publishTime;
        this.status = builder.status;
        this.departmentId = builder.departmentId;
        this.recruitType = builder.recruitType;
        this.interviewRound = builder.interviewRound;
    }
}
