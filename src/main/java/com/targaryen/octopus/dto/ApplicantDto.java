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
@Table(name = "t_applicant")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ApplicantDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_applicant_seq")
    @SequenceGenerator(name = "t_applicant_seq", sequenceName = "t_applicant_seq", allocationSize = 1)
    private int applicantId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.REMOVE)
    private ResumeDto resume;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.REMOVE)
    private List<ApplicationDto> applications;

}
