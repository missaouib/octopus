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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_role_seq")
    @SequenceGenerator(name = "t_role_seq", sequenceName = "t_role_seq", allocationSize = 1)
    private int roleId;

    @NotBlank
    private int userId;

    @NotBlank
    private String role;
}
