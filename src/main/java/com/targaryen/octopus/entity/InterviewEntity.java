package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class InterviewEntity {
    private int applicationId;
    private int interviewerId;
    private String interviewStartTime;
    private String interviewPlace;
}
