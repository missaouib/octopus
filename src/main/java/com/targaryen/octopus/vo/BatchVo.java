package com.targaryen.octopus.vo;

import java.util.Date;

public class BatchVo {
    final private int batchId;
    final private String batchName;
    final private Date year;
    final private int number;
    final private Date startTime;
    final private Date endTime;

    public static class Builder {
        private int batchId;
        private String batchName;
        private Date year;
        private int number;
        private Date startTime;
        private Date endTime;

        public Builder batchId(int batchId) {
            this.batchId = batchId;
            return this;
        }

        public Builder batchName(String batchName) {
            this.batchName = batchName;
            return this;
        }

        public Builder year(Date year) {
            this.year = year;
            return this;
        }

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder startTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public BatchVo build() {
            return new BatchVo(this);
        }
    }

    private BatchVo(Builder builder) {
        this.batchId = builder.batchId;
        this.batchName = builder.batchName;
        this.year = builder.year;
        this.number = builder.number;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }
}
