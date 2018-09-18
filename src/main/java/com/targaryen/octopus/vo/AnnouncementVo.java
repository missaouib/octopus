package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class AnnouncementVo {
    private final int announcementId;
    private final int batchId;
    private final String announcementTitle;
    private final String announcementDetail;
    private final Date createTime;
    private final Date publishedTime;
    private final Date lastModifyTime;
    private final int announcementType;
    private final int announcementStatus;

    public static class Builder {
        private int announcementId;
        private int batchId;
        private String announcementTitle;
        private String announcementDetail;
        private Date createTime;
        private Date publishedTime;
        private Date lastModifyTime;
        private int announcementType;
        private int announcementStatus;

        public Builder announcementId(int announcementId) {
            this.announcementId = announcementId;
            return this;
        }

        public Builder batchId(int batchId) {
            this.batchId = batchId;
            return this;
        }

        public Builder announcementTitle(String announcementTitle) {
            this.announcementTitle = announcementTitle;
            return this;
        }

        public Builder announcementDetail(String announcementDetail) {
            this.announcementDetail = announcementDetail;
            return this;
        }

        public Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder publishedTime(Date publishedTime) {
            this.publishedTime = publishedTime;
            return this;
        }

        public Builder lastModifyTime(Date lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
            return this;
        }

        public Builder annoucementType(int announcementType) {
            this.announcementType = announcementType;
            return this;
        }

        public Builder announcementStatus(int announcementStatus) {
            this.announcementStatus = announcementStatus;
            return this;
        }

        public AnnouncementVo build() {
            return new AnnouncementVo(this);
        }

    }

    private AnnouncementVo(Builder builder) {
        this.announcementId = builder.announcementId;
        this.batchId = builder.batchId;
        this.announcementDetail = builder.announcementDetail;
        this.announcementStatus = builder.announcementStatus;
        this.announcementTitle = builder.announcementTitle;
        this.announcementType = builder.announcementType;
        this.createTime = builder.createTime;
        this.lastModifyTime = builder.lastModifyTime;
        this.publishedTime = builder.publishedTime;
    }
}
