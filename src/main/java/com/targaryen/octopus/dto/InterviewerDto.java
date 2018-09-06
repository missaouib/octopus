package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_interviewer_seq")
    @SequenceGenerator(name = "t_interviewer_seq", sequenceName = "t_interviewer_seq", allocationSize = 1)
    private int interviewerId;


    private String interviewerName;


    private String interviewerPosition;


    private int interviewerAge;


    private String interviewerDepartment;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @OneToMany(mappedBy = "interviewer")
    private List<InterviewDto> interviews;
}
