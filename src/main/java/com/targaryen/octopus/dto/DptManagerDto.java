package com.targaryen.octopus.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 *  @author He Junfeng
 * */
@Data
@Entity
@Table(name = "t_department_manager")
public class DepartmentManagerDto {
    @Id
    @GeneratedValue
    private int departmentManagerId;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.MERGE)
    private UserDto user;

    @OneToMany(mappedBy = "departmentManager", cascade = CascadeType.REMOVE)
    private List<PostDto> posts;
}
