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

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    private Date interviewStartTime;

    @NotBlank
    private String interviewPlace;

    @NotBlank
    private int applicantStatus;

    private String applicantComment;

    @NotBlank
    private int interviewerStatus;

    private String interviewerComment;

    @NotBlank
    private int reservationStatus;

    @NotBlank
    private int interviewResultStatus;

    private String interviewResultComment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationResultTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date interviewResultTime;

    @JoinColumn(name = "application_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ApplicationDto application;

    @JoinColumn(name = "interviewer_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private InterviewerDto interviewer;
}
