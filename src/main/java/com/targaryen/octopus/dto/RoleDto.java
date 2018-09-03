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
    @Column(name = "role_id")
    private int roleId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @Column(name = "role")
    private String role;
}
