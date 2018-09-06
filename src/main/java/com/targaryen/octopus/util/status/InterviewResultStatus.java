package com.targaryen.octopus.util.status;

public class InterviewResultStatus {
    final static public int INIT = 0;
    final static public int FAIL = -1;
    final static public int PASS = 1;

    public static String toString(int status) {
        switch (status) {
            case INIT:
                return "pending";
            case FAIL:
                return "fail";
            case PASS:
                return "pass";
            default:
                return "invalid";
        }
    }
}
