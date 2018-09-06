package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zhouy on 2018/9/6.
 */
@Data
@NoArgsConstructor
public class ApplicantCommentEntity {

    private int interviewId;
    private int applicantStatus;
    private String applicantComment;
}
