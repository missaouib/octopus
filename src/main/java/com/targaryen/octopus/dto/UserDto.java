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
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private RoleDto role;
}
