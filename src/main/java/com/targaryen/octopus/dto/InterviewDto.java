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
    @GeneratedValue
    private int interviewId;

    @NotBlank
    private int applicationId;
    private int interviewerId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    private String interviewPlace;

    @NotBlank
    private int interviewerStatus;
    private String interviewComment;
}
