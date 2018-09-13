package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.Date;

/**
 * @author Liu Mengyang
 */
@Getter
public class EducationExperienceVo {

    final private int educationExperienceId;
    final private int resumeId;
    final private Date startTime;
    final private Date endTime;
    final private String school;
    final private String major;
    final private String typeOfStudy;
    final private String degree;

    public static class Builder {
        private int educationExperienceId;
        private int resumeId;
        private Date startTime;
        private Date endTime;
        private String school;
        private String major;
        private String typeOfStudy;
        private String degree;

        public Builder educationExperienceId(int educationExperienceId) {
            this.educationExperienceId = educationExperienceId;
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

        public Builder school(String school) {
            this.school = school;
            return this;
        }

        public Builder major(String major) {
            this.major = major;
            return this;
        }

        public Builder typeOfStudy(String typeOfStudy) {
            this.typeOfStudy = typeOfStudy;
            return this;
        }
        public Builder degree(String degree) {
            this.degree = degree;
            return this;
        }

        public EducationExperienceVo build() {
            return new EducationExperienceVo(this);
        }
    }

    private EducationExperienceVo(Builder builder) {
        this.educationExperienceId = builder.educationExperienceId;
        this.resumeId = builder.resumeId;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.school = builder.school;
        this.major = builder.major;
        this.typeOfStudy = builder.typeOfStudy;
        this.degree = builder.degree;
    }
}
