package com.targaryen.octopus.vo;

import lombok.Data;

@Data
public class PostVo {
    private final int postId;

    private final String postName;

    private final String postType;

    private final String postLocale;

    private final String postDescription;

    private final String postRequirement;

    private final int status;

    public static class Builder {
        private int postId;
        private String postName;
        private String postType;
        private String postLocale;
        private String postDescription;
        private String postRequirement;
        private int status;

        public Builder postId(int postId) {
            this.postId = postId;
            return this;
        }

        public Builder postName(String postName) {
            this.postName = postName;
            return this;
        }

        public Builder postType(String postType) {
            this.postType = postType;
            return this;
        }

        public Builder postLocale(String postLocale) {
            this.postLocale = postLocale;
            return this;
        }

        public Builder postDescription(String postDescription) {
            this.postDescription = postDescription;
            return this;
        }

        public Builder postRequirement(String requirement) {
            this.postRequirement = requirement;
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
        this.postType = builder.postType;
        this.postLocale = builder.postLocale;
        this.postDescription = builder.postDescription;
        this.postRequirement = builder.postRequirement;
        this.status = builder.status;
    }
}
