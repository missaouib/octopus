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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_dpt_manager_seq")
    @SequenceGenerator(name = "t_dpt_manager_seq", sequenceName = "t_dpt_manager_seq", allocationSize = 1)
    private int dptManagerId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @OneToMany(mappedBy = "dptManager", cascade = CascadeType.REMOVE)
    @OrderBy("post_id DESC")
    private List<PostDto> posts;
}
