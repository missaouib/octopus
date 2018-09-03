package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  @author Liu Mengyang
 * */
@Entity
@Table(name = "t_candidate")
@EntityListeners(AuditingEntityListener.class)
@Data
public class CandidateDto implements Serializable {
    @Id
    private int userId;

    @NotBlank
    private String candidateName;
    private int candidateSex;
    private int candidateAge;
    private String candidateSchool;
    private int candidateDegree;
    private String candidateMajor;
    private String candidateCity;
    private String candidateEmail;
    private String candidatePhone;
    private String candidateCV;
}
