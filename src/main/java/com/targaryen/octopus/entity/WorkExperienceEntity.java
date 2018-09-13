package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by zhouy on 2018/9/13.
 */
@Data
@NoArgsConstructor
public class WorkExperienceEntity {

     private int workExperienceId;
     private int resumeId;
     private String startTime;
     private String endTime;
     private String company;
     private String post;
     private String city;
     private String referenceName;
     private String referencePhoneNum;
     private String workDescription;
     private String achievement;
}
