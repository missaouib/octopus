package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
*  @author Liu Mengyang
* */

@Entity
@Table(name = "t_application")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ApplicationDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_application_seq")
    @SequenceGenerator(name = "t_application_seq", sequenceName = "t_application_seq", allocationSize = 1)
    private int applicationId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date filterEndTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date interviewEndTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dptApproveEndTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date offerTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date applicantFeedbackTime;

    @NotNull
    private int status;

    @OneToMany(mappedBy = "application", cascade = CascadeType.REMOVE)
    private List<InterviewDto> interviews;

    @JoinColumn(name = "applicant_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private ApplicantDto applicant;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private PostDto post;
}
