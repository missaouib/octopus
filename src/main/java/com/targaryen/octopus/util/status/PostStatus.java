package com.targaryen.octopus.util.status;

public class PostStatus {
    final static public Integer FINISHED = 2;
    final static public Integer PUBLISHED = 1;
    final static public Integer REVOKED = -1;
    final static public Integer INIT = 0;

    public static String toString(int status) {
        if (status == FINISHED)
            return "finished";
        else if (status == PUBLISHED)
            return "published";
        else if (status == REVOKED)
            return "revoked";
        else if (status == INIT)
            return "init";
        else
            return "invalid";
    }
}
