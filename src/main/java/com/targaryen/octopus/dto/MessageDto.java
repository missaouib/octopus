package com.targaryen.octopus.dto;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "t_message")
@EntityListeners(AuditingEntityListener.class)
public class MessageDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_message_seq")
    @SequenceGenerator(name = "t_message_seq", sequenceName = "t_message_seq", allocationSize = 1)
    private int messageId;

    private String subject;

    private String object;

    @NotBlank
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private String link;

    private String channel;

    private int messageType;

    /* Temp */

    @Transient
    private String createTimeString;

    @Transient
    private String iconName;

    @Transient
    private String iconColor;
}
