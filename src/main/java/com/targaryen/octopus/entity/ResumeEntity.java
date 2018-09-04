package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResumeEntity {
    private int resumeId;
    private String applicantName;
    private int applicantSex;
    private int applicantAge;
    private String applicantSchool;
    private int applicantDegree;
    private String applicantMajor;
    private String applicantCity;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantCV;
}
