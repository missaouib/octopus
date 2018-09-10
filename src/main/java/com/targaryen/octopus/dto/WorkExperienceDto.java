package com.targaryen.octopus.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_work_experience")
public class WorkExperienceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_work_experience_seq")
    @SequenceGenerator(name = "t_work_experience_seq", sequenceName = "t_work_experience_seq", allocationSize = 1)
    private int workExperienceId;

    @JoinColumn(name = "resume_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ResumeDto resume;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private String company;
    private String post;
    private String city;
    private String referenceName;
    private String referencePhoneNum;
    private String workDiscription;
    private String achievement;

}
