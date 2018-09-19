package com.targaryen.octopus.util.status;

public class SourceType {
    public final static Integer SCHOOL = 0;
    public final static Integer CITY = 1;
    public final static Integer POST = 2;

    public static String toString(int status) {
        if(status == SCHOOL)
            return "school";
        else if(status == CITY)
            return "city";
        else if(status == POST)
            return "post";
        else
            return "invalid";
    }
}
