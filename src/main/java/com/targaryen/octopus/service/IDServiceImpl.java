package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.UserDtoRepository;
import com.targaryen.octopus.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *  Created by Liu Mengyang on 2018/09/05
 */
@Service
public class IDServiceImpl implements IDService {
    private UserDtoRepository userDtoRepository;

    @Autowired
    public IDServiceImpl(DaoFactory daoFactory) {
        this.userDtoRepository = daoFactory.getUserDtoRepository();
    }

    public int userIdToApplicantId(int userId) {
        UserDto userDto;
        ApplicantDto applicantDto;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return -1;
            applicantDto = userDto.getApplicant();
            if(applicantDto == null)
                return -1;
        } catch (DataAccessException e) {
            return -1;
        }

        return applicantDto.getApplicantId();
    }

    public int userIdToHrId(int userId) {
        UserDto userDto;
        HRDto hrDto;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return -1;
            hrDto = userDto.getHr();
            if(hrDto == null)
                return -1;
        } catch (DataAccessException e) {
            return -1;
        }

        return hrDto.getHrId();
    }

    public int userIdToDptManagerId(int userId) {
        UserDto userDto;
        DptManagerDto dptManagerDto;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return -1;
            dptManagerDto = userDto.getDptManager();
            if(dptManagerDto == null)
            return -1;
        } catch (DataAccessException e) {
            return -1;
        }

        return dptManagerDto.getDptManagerId();
    }

    public int userIdToInterviewerId(int userId) {
        UserDto userDto;
        InterviewerDto interviewerDto;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return -1;
            interviewerDto = userDto.getInterviewer();
            if(interviewerDto == null)
            return -1;
        } catch (DataAccessException e) {
            return -1;
        }

        return interviewerDto.getInterviewerId();
    }
}
