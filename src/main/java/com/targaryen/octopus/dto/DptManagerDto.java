package com.targaryen.octopus.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 *  @author He Junfeng
 * */
@Data
@Entity
@Table(name = "t_dpt_manager")
public class DptManagerDto {
    @Id
    @GeneratedValue
    private int dptManagerId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @OneToMany(mappedBy = "dptManager", cascade = CascadeType.REMOVE)
    private List<PostDto> posts;
}
