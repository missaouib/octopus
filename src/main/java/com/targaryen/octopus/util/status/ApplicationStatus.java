package com.targaryen.octopus.util.status;

public class ApplicationStatus {
    final static public int INIT = 0;
    final static public int FILTER_PASS = 1;
    final static public int FILTER_FAIL = -1;
    final static public int INTERVIEW_PASS = 2;
    final static public int INTERVIEW_FAIL = -2;
    final static public int DPT_PASS = 3;
    final static public int DPT_FAIL = -3;
    final static public int APPLICANT_ACCEPT = 4;
    final static public int APPLICANT_REJECT = -4;
    
    public static String toString(int status) {
        switch (status) {
            case INIT:
                return "init";
            case FILTER_PASS:
                return "filter pass";
            case FILTER_FAIL:
                return "filter fail";
            case INTERVIEW_PASS:
                return "interview pass";
            case INTERVIEW_FAIL:
                return "interview fail";
            case DPT_PASS:
                return "department manager pass";
            case DPT_FAIL:
                return "department manager fail";
            case APPLICANT_ACCEPT:
                return "applicant accept";
            case APPLICANT_REJECT:
                return "applicant reject";
            default:
                return "invalid";
        }
    }
}
