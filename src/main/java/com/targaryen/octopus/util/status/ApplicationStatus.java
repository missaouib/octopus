package com.targaryen.octopus.util.status;

import javax.persistence.criteria.CriteriaBuilder;

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
}
