package com.targaryen.octopus.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_education_experience")
public class EducationExperienceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_education_experience_seq")
    @SequenceGenerator(name = "t_education_experience_seq", sequenceName = "t_education_experience_seq", allocationSize = 1)
    private int educationExperienceId;

    @JoinColumn(name = "resume_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ResumeDto resume;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private String school;
    private String major;
    private String typeOfStudy;
    private String degree;
}
