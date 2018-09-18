package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class PostRecruitProgressVo {
    private final int postId;
    private final String postName;
    private final int postStatus;
    private final Date publishTime;
    private final int recruitNum;
    private final int applicationNum;
    private final int filterPassNum;
    private final int interviewPassNum;
    private final int dptPassNum;
    private final int offerNum;
    private final int entryNum;

    public static class Builder {
        private int postId;
        private String postName;
        private int postStatus;
        private Date publishTime;
        private int recruitNum;
        private int applicationNum;
        private int filterPassNum;
        private int interviewPassNum;
        private int dptPassNum;
        private int offerNum;
        private int entryNum;

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder postName(String postName) {
            this.postName = postName;
            return this;
        }

        public Builder postStatus(int postStatus) {
            this.postStatus = postStatus;
            return this;
        }

        public Builder publishTime(Date publishTime) {
            this.publishTime = publishTime;
            return this;
        }

        public Builder recruitNum(int recruitNum) {
            this.recruitNum = recruitNum;
            return this;
        }

        public Builder applicationNum(int applicationNum) {
            this.applicationNum = applicationNum;
            return this;
        }

        public Builder filterPassNum(int filterPassNum) {
            this.filterPassNum = filterPassNum;
            return this;
        }

        public Builder interviewPassNum(int interviewPassNum) {
            this.interviewPassNum = interviewPassNum;
            return this;
        }

        public Builder dptPassNum(int dptPassNum) {
            this.dptPassNum = dptPassNum;
            return this;
        }

        public Builder offerNum(int offerNum) {
            this.offerNum = offerNum;
            return this;
        }

        public Builder entryNum(int entryNum) {
            this.entryNum = entryNum;
            return this;
        }

        public PostRecruitProgressVo build() {
            return new PostRecruitProgressVo(this);
        }
    }

    public PostRecruitProgressVo(Builder builder) {
        this.postId = builder.postId;
        this.postName = builder.postName;
        this.applicationNum = builder.applicationNum;
        this.dptPassNum = builder.dptPassNum;
        this.entryNum = builder.entryNum;
        this.filterPassNum = builder.filterPassNum;
        this.interviewPassNum = builder.interviewPassNum;
        this.offerNum = builder.offerNum;
        this.postStatus = builder.postStatus;
        this.publishTime = builder.publishTime;
        this.recruitNum = builder.recruitNum;
    }
}
