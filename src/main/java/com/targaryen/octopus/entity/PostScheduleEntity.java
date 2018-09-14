package com.targaryen.octopus.entity;

import com.targaryen.octopus.vo.InterviewVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class PostScheduleEntity {

    private List<String> uniqueDates;

    private Map<String, Map<String, List<InterviewVo>>> interviewMapThisRound;

    private int interviewOverallCount;

    private String overallStartDate;

    private String overallFinalDate;
}
