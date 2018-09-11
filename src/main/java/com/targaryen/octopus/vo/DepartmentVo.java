package com.targaryen.octopus.vo;

import lombok.Getter;

import java.util.List;

/**
 * @author Liu Mengyang
 */
@Getter
public class DepartmentVo {
    final private int departmentId;
    final private String departmentName;

    public static class Builder {
        private int departmentId;
        private String departmentName;

        public Builder departmentId(int departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public DepartmentVo build() {
            return new DepartmentVo(this);
        }
    }

    private DepartmentVo(Builder builder) {
        this.departmentId = builder.departmentId;
        this.departmentName = builder.departmentName;
    }

}
