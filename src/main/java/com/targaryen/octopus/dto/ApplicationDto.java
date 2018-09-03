package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
    @GeneratedValue
    private int applicationId;

    @NotBlank
    private int status;

    @OneToMany(mappedBy = "application", cascade = CascadeType.REMOVE)
    private List<InterviewDto> interviews;

    @JoinColumn(name = "applicant_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private ApplicantDto applicant;

    @JoinColumn(name = "post_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private PostDto post;
}
