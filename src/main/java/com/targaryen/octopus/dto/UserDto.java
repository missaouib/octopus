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
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserDto implements Serializable {
    @Id
    @GeneratedValue
    private int userId;

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;
}
