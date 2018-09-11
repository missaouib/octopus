package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by zhouy on 2018/9/10.
 */
@Data
@Getter
@Setter
@NoArgsConstructor
public class InterviewerCommentEntity {

    private int interviewId;
    private int applicationId;
    private int interviewResultStatus;
    private String interviewerComment;
}
