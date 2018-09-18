package com.targaryen.octopus.util.status;

public class AnnouncementStauts {
    public final static Integer DRAFT = 0;
    public final static Integer PUBLISHED = 1;

    public static String toString(int status) {
        if(status == DRAFT)
            return "draft";
        else if(status == PUBLISHED)
            return "published";
        else
            return "invalid";
    }
}
