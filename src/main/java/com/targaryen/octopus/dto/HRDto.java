package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 *  @author He Junfeng
 * */
@Data
@Entity
@Table(name = "t_hr")
@EntityListeners(AuditingEntityListener.class)
public class HRDto {
    @Id
    @GeneratedValue
    private int hrId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

}
