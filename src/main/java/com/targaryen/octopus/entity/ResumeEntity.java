package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResumeEntity {

    private int resumeId;
    private int applicantId;
    private int postId;
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
    private String applicantHometown;
    private String applicantNation;
    private String applicantPoliticalStatus;
    private String applicantMaritalStatus;
    private String applicantDateOfBirth;
    private String applicantTimeToWork;
    private int applicantCurrentSalary;
    private int applicantExpectSalary;
    private String applicantDutyTime;
    private String recommenderName;
    private String applicantAddress;
    private String applicantSelfIntro;
    private String applicantPhoto;
    private String applicantDegreePhoto;
    private String familyContactRelation;
    private String familyContactName;
    private String familyContactCompany;
    private String familyContactPhoneNum;
}
