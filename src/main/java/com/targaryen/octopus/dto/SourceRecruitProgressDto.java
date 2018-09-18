package com.targaryen.octopus.dto;

import lombok.Data;

@Data
public class SourceRecruitProgressDto {
    private String sourceName;
    private int sourceType;
    private int applicationNum;
    private int filterPassNum;
    private int interviewPassNum;
    private int dptPassNum;
    private int offerNum;
    private int entryNum;
}
