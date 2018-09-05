package com.targaryen.octopus.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  @author Liu Mengyang
 * */
@Entity
@Table(name = "t_user")
@Data
public class UserDto implements Serializable {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_user_seq")
    @SequenceGenerator(name = "t_user_seq", sequenceName = "t_user_seq", allocationSize = 1)
    private int userId;

    @NotBlank
    @Column(name = "user_name", unique = true)
    private String userName;

    @NotBlank
    @Column(name = "user_password")
    private String userPassword;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private RoleDto role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private HRDto hr;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private ApplicantDto applicant;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private InterviewerDto interviewer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private DptManagerDto dptManager;

}
