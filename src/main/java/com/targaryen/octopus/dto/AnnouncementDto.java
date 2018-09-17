package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "t_announcement")
@EntityListeners(AuditingEntityListener.class)
public class AnnouncementDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_announcement_seq")
    @SequenceGenerator(name = "t_announcement_seq", sequenceName = "t_announcement_seq", allocationSize = 1)
    private int announcementId;

    @JoinColumn(name = "batch_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private BatchDto batch;

    @NotBlank
    private String announcementTitle;

    @NotBlank
    private String announcementDetail;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedTime;

    private int announcementType;

    private int announcementStatus;
}
