package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 *  @author Liu Mengyang
 * */
@Entity
@Table(name = "t_interview")
@EntityListeners(AuditingEntityListener.class)
@Data
public class InterviewDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_interview_seq")
    @SequenceGenerator(name = "t_interview_seq", sequenceName = "t_interview_seq", allocationSize = 1)
    private int interviewId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    private String interviewPlace;

    @NotBlank
    private int applicantStatus;

    private String applicantComment;

    @NotBlank
    private int interviewerStatus;

    private String interviewerComment;

    @NotBlank
    private int interviewStatus;

    @NotBlank
    private int interviewResultStatus;

    private String interviewResultComment;

    @JoinColumn(name = "application_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ApplicationDto application;

    @JoinColumn(name = "interviewer_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private InterviewerDto interviewer;
}
