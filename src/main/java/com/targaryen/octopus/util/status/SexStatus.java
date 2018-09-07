package com.targaryen.octopus.util.status;

public class SexStatus {
    public final static Integer MALE = 0;
    public final static Integer FEMALE = 1;

    public static String toString(int status) {
        if(status == MALE)
            return "male";
        else if(status == FEMALE)
            return "female";
        else
            return "invalid";
    }
}
