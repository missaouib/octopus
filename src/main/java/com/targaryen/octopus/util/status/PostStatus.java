package com.targaryen.octopus.util.status;

public class PostStatus {
    final static public int PUBLISHED = 1;
    final static public int REVOKED = -1;
    final static public int INIT = 0;

    public static String toString(int status) {
        switch (status) {
            case PUBLISHED:
                return "published";
            case REVOKED:
                return "revoked";
            case INIT:
                return "init";
            default:
                return "invalid";
        }
    }
}
