package com.targaryen.octopus.util.status;

public class ApplicationStatus {
    final static public Integer INIT = 0;
    final static public Integer FILTER_PASS = 1;
    final static public Integer FILTER_FAIL = -1;
    final static public Integer INTERVIEW_PASS = 2;
    final static public Integer INTERVIEW_FAIL = -2;
    final static public Integer DPT_PASS = 3;
    final static public Integer DPT_FAIL = -3;
    final static public Integer OFFER = 4;
    final static public Integer APPLICANT_ACCEPT = 5;
    final static public Integer APPLICANT_REJECT = -5;
    
    public static String toString(int status) {
        if(status == INIT)
            return "init";
        else if(status == FILTER_PASS)
            return "filter pass";
        else if(status == FILTER_FAIL)
            return "filter fail";
        else if(status == INTERVIEW_PASS)
            return "interview pass";
        else if(status == INTERVIEW_FAIL)
            return "interview fail";
        else if(status == DPT_PASS)
            return "department manager pass";
        else if(status == DPT_FAIL)
            return "department manager fail";
        else if(status == OFFER)
            return "offer sent";
        else if(status == APPLICANT_ACCEPT)
            return "applicant accept";
        else if(status == APPLICANT_REJECT)
            return "applicant reject";
        else
            return "invalid";
    }
}
