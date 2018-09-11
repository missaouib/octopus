package com.targaryen.octopus.util.status;

public class RecruitTypeStatus {
    public final static Integer CAMPUS = 0;
    public final static Integer SOCIETY = 1;

    public static String toString(int status) {
        if (status == CAMPUS)
            return "campus";
        else if (status == SOCIETY)
            return  "society";
        else
            return "invalid";
    }
}
