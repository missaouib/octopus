package com.targaryen.octopus.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "notes")
public class NoteVo {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

}
