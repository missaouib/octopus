package com.targaryen.octopus.util.status;

public class InterviewResultStatus {
    final static public Integer INIT = 0;
    final static public Integer NOT_HIRE = -1;
    final static public Integer WEAK_HIRE = 1;
    final static public Integer HIRE = 2;
    final static public Integer STRONG_HIRE = 3;

    public static String toString(int status) {
        if(status == INIT)
            return "not evaluated";
        else if (status == NOT_HIRE)
            return "not hire";
        else if (status == WEAK_HIRE)
            return "weak hire";
        else if (status == HIRE)
            return "hire";
        else if (status == STRONG_HIRE)
            return "strong hire";
        else
            return "invalid";
    }
}
