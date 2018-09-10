package com.targaryen.octopus.vo;

import java.util.Date;

public class WorkExperienceVo {
    final private int workExperienceId;
    final private int resumeId;
    final private Date startTime;
    final private Date endTime;
    final private String company;
    final private String post;
    final private String city;
    final private String referenceName;
    final private String referencePhoneNum;
    final private String workDescription;
    final private String achievement;

    public static class Builder {
        private int workExperienceId;
        private int resumeId;
        private Date startTime;
        private Date endTime;
        private String company;
        private String post;
        private String city;
        private String referenceName;
        private String referencePhoneNum;
        private String workDescription;
        private String achievement;

        public Builder workExperienceId(int workExperienceId) {
            this.workExperienceId = workExperienceId;
            return this;
        }

        public Builder resumeId(int resumeId) {
            this.resumeId = resumeId;
            return this;
        }

        public Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder post(String post) {
            this.post = post;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder referenceName(String referenceName) {
            this.referenceName = referenceName;
            return this;
        }

        public Builder referencePhoneNum(String referencePhoneNum) {
            this.referencePhoneNum = referencePhoneNum;
            return this;
        }

        public Builder workDescription(String workDescription) {
            this.workDescription = workDescription;
            return this;
        }

        public Builder achievement(String achievement) {
            this.achievement = achievement;
            return this;
        }

        public WorkExperienceVo build() {
            return new WorkExperienceVo(this);
        }
    }

    private WorkExperienceVo(Builder builder) {
        this.workExperienceId = builder.workExperienceId;
        this.resumeId = builder.resumeId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.company = builder.company;
        this.post = builder.post;
        this.city = builder.city;
        this.referenceName = builder.referenceName;
        this.referencePhoneNum = builder.referencePhoneNum;
        this.workDescription = builder.workDescription;
        this.achievement = builder.achievement;
    }
}
