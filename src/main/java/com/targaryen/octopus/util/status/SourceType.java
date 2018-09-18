package com.targaryen.octopus.util.status;

public class SourceType {
    public final static Integer SCHOOL = 0;
    public final static Integer CITY = 1;

    public static String toString(int status) {
        if(status == SCHOOL)
            return "school";
        else if(status == CITY)
            return "city";
        else
            return "invalid";
    }
}
