package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class SourceRecruitProgressVo {
    private final String sourceName;
    private final int sourceType;
    private final int applicationNum;
    private final int filterPassNum;
    private final int interviewPassNum;
    private final int dptPassNum;
    private final int offerNum;
    private final int entryNum;

    public static class Builder {
        private String sourceName;
        private int sourceType;
        private int applicationNum;
        private int filterPassNum;
        private int interviewPassNum;
        private int dptPassNum;
        private int offerNum;
        private int entryNum;

        public Builder sourceName(String sourceName) {
            this.sourceName = sourceName;
            return this;
        }

        public Builder sourceType(int sourceType) {
            this.sourceType = sourceType;
            return this;
        }

        public Builder applicationNum(int applicationNum) {
            this.applicationNum = applicationNum;
            return this;
        }

        public Builder filterPassNum(int filterPassNum) {
            this.filterPassNum = filterPassNum;
            return this;
        }

        public Builder interviewPassNum(int interviewPassNum) {
            this.interviewPassNum = interviewPassNum;
            return this;
        }

        public Builder dptPassNum(int dptPassNum) {
            this.dptPassNum = dptPassNum;
            return this;
        }

        public Builder offerNum(int offerNum) {
            this.offerNum = offerNum;
            return this;
        }

        public Builder entryNum(int entryNum) {
            this.entryNum = entryNum;
            return this;
        }

        public SourceRecruitProgressVo build() {
            return new SourceRecruitProgressVo(this);
        }

    }

    private SourceRecruitProgressVo(Builder builder) {
        this.sourceName = builder.sourceName;
        this.sourceType = builder.sourceType;
        this.applicationNum = builder.applicationNum;
        this.dptPassNum = builder.dptPassNum;
        this.entryNum = builder.entryNum;
        this.filterPassNum = builder.filterPassNum;
        this.interviewPassNum = builder.interviewPassNum;
        this.offerNum = builder.offerNum;
    }
}
