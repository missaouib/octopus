package com.targaryen.octopus.dto;


import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "t_source_file")
@EntityListeners(AuditingEntityListener.class)
public class SourceFileDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_source_file_seq")
    @SequenceGenerator(name = "t_source_file_seq", sequenceName = "t_source_file_seq", allocationSize = 1)
    private int sourceFileId;

    @JoinColumn(name = "batch_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private BatchDto batch;

    @NotBlank
    private String sourceName;

    private int applicationNum;

    private int filterPassNum;

    private int interviewPassNum;

    private int offerNum;

    private int entryNum;
}
