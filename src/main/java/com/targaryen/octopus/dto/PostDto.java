package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    private int recruitNum;

    private String recruitDpt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime;

    private int status;

    @JoinColumn(name = "dpt_manager_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private DptManagerDto dptManager;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<ApplicationDto> applications;
}
