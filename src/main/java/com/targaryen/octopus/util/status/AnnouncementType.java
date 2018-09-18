package com.targaryen.octopus.util.status;

public class AnnouncementType {
    public final static Integer HR_NOT_VIEW = 0;
    public final static Integer HR_VIEW = 1;

    public static String toString(int status) {
        if(status == HR_NOT_VIEW)
            return "hr_not_view";
        else if(status == HR_VIEW)
            return "hr_view";
        else
            return "invalid";
    }
}
