package com.targaryen.octopus.service;

/**
 *  Created by Liu Mengyang on 2018/09/05
 */
public interface IDService {
    int userIdToApplicantId(int userId);
    int userIdToHrId(int userId);
    int userIdToDptManagerId(int userId);
    int userIdToInterviewerId(int userId);
}
