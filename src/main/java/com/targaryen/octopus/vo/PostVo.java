package com.targaryen.octopus.vo;

import lombok.Data;

@Data
public class PostVo {
    private final int postId;

    private final String postName;

    private final String postDescription;

    private final String requirement;

    private final int status;

    public static class Builder {
        private int postId;
        private String postName;
        private String postDescription;
        private String requirement;
        private int status;

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder postName(String postName) {
            this.postName = postName;
            return this;
        }

        public Builder postDescription(String postDescription) {
            this.postDescription = postDescription;
            return this;
        }

        public Builder requirement(String requirement) {
            this.requirement = requirement;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public PostVo build() {
            return new PostVo(this);
        }
    }

    private PostVo(Builder builder) {
        this.postId = builder.postId;
        this.postName = builder.postName;
        this.postDescription = builder.postDescription;
        this.requirement = builder.requirement;
        this.status = builder.status;
    }
}
