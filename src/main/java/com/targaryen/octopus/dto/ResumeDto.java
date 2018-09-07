package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  @author He Junfeng
 * */

@Data
@Entity
@Table(name = "t_resume")
@EntityListeners(AuditingEntityListener.class)
public class ResumeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_resume_seq")
    @SequenceGenerator(name = "t_resume_seq", sequenceName = "t_resume_seq", allocationSize = 1)
    private int resumeId;

    @NotBlank
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

    @JoinColumn(name = "applicant_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private ApplicantDto applicant;

}
