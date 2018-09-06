package com.targaryen.octopus.util.status;

public class InterviewStatus {
    final static public Integer INIT = 0;
    final static public Integer FAIL = -1;
    final static public Integer SUCCESS = 1;

    public static String toString(int status) {
        if (status == INIT)
            return "pending";
        else if (status == FAIL)
            return "fail";
        else if (status == SUCCESS)
            return "success";
        else
            return "invalid";
    }
}
