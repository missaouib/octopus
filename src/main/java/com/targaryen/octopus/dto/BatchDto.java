package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_batch")
@EntityListeners(AuditingEntityListener.class)
public class BatchDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_batch_seq")
    @SequenceGenerator(name = "t_batch_seq", sequenceName = "t_batch_seq", allocationSize = 1)
    private int batchId;

    @NotBlank
    private String batchName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date year;

    @NotNull
    private int number;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.REMOVE)
    private List<SourceFileDto> sourceFiles;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.REMOVE)
    private List<AnnouncementDto> announcements;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.REMOVE)
    private List<PostDto> posts;
}
