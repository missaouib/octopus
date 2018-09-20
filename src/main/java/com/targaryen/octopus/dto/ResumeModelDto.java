package com.targaryen.octopus.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_resume_model")
public class ResumeModelDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_resume_model_seq")
    @SequenceGenerator(name = "t_resume_model_seq", sequenceName = "t_resume_model_seq", allocationSize = 1)
    private int resumeModelId;

    @JoinColumn(name = "post_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private PostDto post;

    private boolean applicantName = true;
    private boolean applicantSex = true;
    private boolean applicantAge = true;
    private boolean applicantSchool = true;
    private boolean applicantDegree = true;
    private boolean applicantMajor = true;
    private boolean applicantCity = true;
    private boolean applicantEmail = true;
    private boolean applicantPhone = true;
    private boolean applicantCV = true;
    private boolean applicantHometown = true;
    private boolean applicantNation = true;
    private boolean applicantPoliticalStatus = true;
    private boolean applicantMaritalStatus = true;
    private boolean applicantDateOfBirth = true;
    private boolean applicantTimeToWork = true;
    private boolean applicantCurrentSalary = true;
    private boolean applicantExpectSalary = true;
    private boolean applicantDutyTime = true;
    private boolean recommenderName = true;
    private boolean applicantAddress = true;
    private boolean applicantSelfIntro = true;
    private boolean applicantPhoto = true;
    private boolean applicantDegreePhoto = false;
    private boolean familyContactRelation = true;
    private boolean familyContactName = true;
    private boolean familyContactCompany = true;
    private boolean familyContactPhoneNum = true;
}
