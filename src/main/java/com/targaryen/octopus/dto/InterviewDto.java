package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date interviewStartTime;

    @NotNull
    private String interviewPlace;

    @NotNull
    private int applicantStatus;

    private String applicantComment;

    @NotNull
    private int interviewerStatus;

    private String interviewerComment;

    @NotNull
    private int reservationStatus;

    @NotNull
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
