package com.targaryen.octopus.util.status;

public class HRMessage {
    public final static int DPT_CREATE_POST = 1; // Department manager created a new post
    public final static int DPT_UPDATE_POST = 2; // Department manager updated a new post
    public final static int DPT_REVOKE_POST = 3; // Department manager revoked a new post

    public final static int APP_APPLY_POST = 4; // Applicant applies for a post

    public final static int DPT_PASS_APPS = 5; // Department manager passed applicants
    public final static int DPT_REJECT_APPS = 6; // Department manager rejected applicants

    public final static int APP_ACCEPT_INTERVIEW = 7; // Applicant accepted interview
    public final static int APP_REJECT_INTERVIEW = 8; // Applicant rejected interview
    public final static int WER_ACCEPT_INTERVIEW = 9; // Interviewer accepted interview
    public final static int WER_REJECT_INTERVIEW = 10; // Interviewer rejected interview

    public final static int WER_COMMENT_INTERVIEW = 11; // Interviewer commented interview
}
