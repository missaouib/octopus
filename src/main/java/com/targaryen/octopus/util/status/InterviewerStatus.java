package com.targaryen.octopus.util.status;

public class InterviewerStatus {
    final static public int INIT = 0;
    final static public int REJECTED = -1;
    final static public int ACCEPTED = 1;

    public static String toString(int status) {
        switch (status) {
            case INIT:
                return "pending";
            case REJECTED:
                return "rejected";
            case ACCEPTED:
                return "accepted";
            default:
                return "invalid";
        }
    }
}
