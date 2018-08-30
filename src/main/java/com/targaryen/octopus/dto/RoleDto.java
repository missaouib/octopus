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
@Table(name = "t_role")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RoleDto implements Serializable {
    @Id
    @GeneratedValue
    private int roleId;

    @NotBlank
    private int userId;

    @NotBlank
    private String role;
}
