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
@Table(name = "t_interviewer")
@EntityListeners(AuditingEntityListener.class)
@Data
public class InterviewerDto implements Serializable {
    @Id
    @GeneratedValue
    private int interviewerId;

    @NotBlank
    private String interviewerName;

    @NotBlank
    private String interviewerPosition;

    @NotBlank
    private int interviewerAge;

    @NotBlank
    private String interviewerDepartment;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @OneToMany(mappedBy = "interviewer")
    private List<InterviewDto> interviews;
}
