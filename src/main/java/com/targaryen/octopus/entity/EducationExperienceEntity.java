package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by zhouy on 2018/9/13.
 */
@Data
@NoArgsConstructor
public class EducationExperienceEntity {

     private int educationExperienceId;
     private int resumeId;
     private String startTime;
     private String endTime;
     private String school;
     private String major;
     private String typeOfStudy;
     private String degree;
}
