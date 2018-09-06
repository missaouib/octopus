package com.targaryen.octopus.util.status;

public class InterviewResultStatus {
    final static public Integer INIT = 0;
    final static public Integer FAIL = -1;
    final static public Integer PASS = 1;

    public static String toString(int status) {
        if(status == INIT)
            return "not evaluated";
        else if (status == FAIL)
            return "fail";
        else if (status == PASS)
            return "pass";
        else
            return "invalid";
    }
}
