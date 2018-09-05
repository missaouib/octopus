package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.ResumeVo;

import java.util.List;

/**
 * Created by Liu Mengyang on 2018/09/05
 */
public interface InterviewerService {
    List<InterviewVo> listInterviewsByUserId(int userId);
    ResumeVo findResumeByApplicationId(int applicationId);
    ResumeVo findResumeByInterviewId(int interviewId);
}
