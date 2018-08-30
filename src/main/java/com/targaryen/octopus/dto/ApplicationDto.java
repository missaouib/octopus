package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
    private int candidateId;

    @NotBlank
    private int jobId;

    @NotBlank
    private int status;
}
