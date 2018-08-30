package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.naming.Name;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  @author Liu Mengyang
 * */
@Entity
@Table(name = "t_job")
@EntityListeners(AuditingEntityListener.class)
@Data
public class JobDto implements Serializable {
    @Id
    @GeneratedValue
    private int jobId;

    @NotBlank
    private String jobName;

    private String jobDescription;

    private String Requirement;
}
