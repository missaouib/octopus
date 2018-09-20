package com.targaryen.octopus.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Created by zhouy on 2018/9/3.
 */
public class Role {
    final static public String HR = "ROLE_HR";
    final static public String DPT = "ROLE_DPT";
    final static public String APPLICANT = "ROLE_APPLICANT";
    final static public String INTERVIEWER = "ROLE_INTERVIEWER";

    /* Zhao */
    public static String getRoleNameByAuthority() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        switch (authorities.toArray()[0].toString()) {
            case "ROLE_HR":
                return "Human Resources";
            case "ROLE_DPT":
                return "Department Manager";
            case "ROLE_APPLICANT":
                return "Applicant";
            case "ROLE_INTERVIEWER":
                return "Interviewer";
        }
        return "";
    }
}
