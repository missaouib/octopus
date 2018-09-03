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
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_role_seq")
    @SequenceGenerator(name = "t_role_seq", sequenceName = "t_role_seq", allocationSize = 1)
    private int roleId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @Column(name = "role")
    private String role;
}
