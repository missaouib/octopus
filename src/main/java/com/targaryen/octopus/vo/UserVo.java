package com.targaryen.octopus.vo;

import lombok.Data;

@Data
public class UserVo {
    final private int userId;
    final private String userName;
    final private String userPassword;
    final private String userRole;
    final private int departmentId;

    public static class Builder{
        private int userId;
        private String userName;
        private String userPassword;
        private String userRole;
        private int departmentId;

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public Builder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public Builder departmentId(int departmentId) {
            this.departmentId = departmentId;
            return this;
        }

        public UserVo build() {
            return new UserVo(this);
        }
    }

    private UserVo(Builder builder) {
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.userPassword = builder.userPassword;
        this.userRole = builder.userRole;
        this.departmentId = builder.departmentId;
    }
}
