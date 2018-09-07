package com.targaryen.octopus.util.status;

public class ApplicantStatus {
    final static public Integer INIT = 0;
    final static public Integer REJECTED = -1;
    final static public Integer ACCEPTED = 1;


    public static String toString(int status) {
        if(status == INIT)
            return "pending";
        else if(status == REJECTED)
            return "rejected";
        else if(status == ACCEPTED)
            return "accepted";
        else
            return "invalid";
    }
}
