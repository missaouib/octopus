package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  @author Liu Mengyang
 * */
@Entity
@Table(name = "t_post")
@EntityListeners(AuditingEntityListener.class)
@Data
public class PostDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_post_seq")
    @SequenceGenerator(name = "t_post_seq", sequenceName = "t_post_seq", allocationSize = 1)
    private int postId;

    @NotBlank
    private String postName;

    private String postType;

    private String postLocale;

    private String postDescription;

    private String postRequirement;

    @NotNull
    private int recruitType;

    private int recruitNum;

    private String recruitDpt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;

    private int status;

    private int interviewRound;

    @JoinColumn(name = "department_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private DepartmentDto department;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<ApplicationDto> applications;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE)
    private ResumeModelDto resumeModel;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<InterviewDto> interviews;
}
