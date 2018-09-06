package com.targaryen.octopus.util.status;

import javax.persistence.ManyToOne;

public class DegreeStatus {
    public final static Integer HIGH_SCHOOL = 0;
    public final static Integer BACHELOR = 1;
    public final static Integer MASTER = 2;
    public final static Integer DOCTOR = 3;

    public static String toString(int status) {
        if(status == HIGH_SCHOOL)
            return "high_school";
        else if(status == BACHELOR)
            return "bachelor";
        else if(status == MASTER)
            return "master";
        else if(status == DOCTOR)
            return "doctor";
        else
            return "invalid";
    }
}
