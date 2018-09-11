package com.targaryen.octopus.dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "t_department")
public class DepartmentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_department_seq")
    @SequenceGenerator(name = "t_department_seq", sequenceName = "t_department_seq", allocationSize = 1)
    private int departmentId;

    @NotBlank
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<DptManagerDto> dptManagers;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    private List<InterviewerDto> interviewers;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    @OrderBy("post_id DESC")
    private List<PostDto> posts;
}
