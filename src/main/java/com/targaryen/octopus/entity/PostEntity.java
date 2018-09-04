package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostEntity {
    private int postId;

    private String postName;

    private String postType;

    private String postLocale;

    private String postDescription;

    private String postRequirement;

    private int recruitNum;

    private String recruitDpt;

    private Date publishTime;

    private int status;
}
