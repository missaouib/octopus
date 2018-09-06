package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.ApplicantApplicationVo;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.ResumeVo;

import java.util.List;

/**
 *  Created by Liu Mengyang on 2018/09/05
 */
public interface ApplicantService {

    /**
     *
     * save applicant user resume, if no resume saved before it will create one first
     *
     * @param userId
     * userId of current applicant
     *
     * @param resumeVo
     * resumeVo of saved resume, field 'applicantName' must not be blank
     *
     * @return
     * Execution status
     */
    int SaveResume(int userId, ResumeVo resumeVo);

    /**
     *
     * find resume by applicant userId, so the userId must be of an applicant
     *
     * @param userId
     * applicant userId
     *
     * @return
     * resume of the applicant stored, if no resume found return null
     */
    ResumeVo findResumeByUserId(int userId);

    /**
     *
     * find all applications of a applicant by userId, so the userId must be of an applicant
     *
     * @param userId
     * applicant userId
     *
     * @return
     * list of applications, if no application found return empty list
     */
    List<ApplicantApplicationVo> findApplicationsByUserId(int userId);

    /**
     *
     * create a new post application based on applicationVo
     *
     * @param applicationVo
     * application information
     *
     * @return
     * execution status
     */
    int CreateNewApplication(ApplicationVo applicationVo);
    List<InterviewVo> findUnreplyedInterviewByUserId(int userId);
    List<InterviewVo> findAcceptedInterviewByUserId(int userId);
    int updateApplicantStatusOfInterview(InterviewVo interviewVo);
}
