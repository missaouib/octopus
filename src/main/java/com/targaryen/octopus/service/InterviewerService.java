package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.ApplicationResumeVo;
import com.targaryen.octopus.vo.InterviewVo;
import com.targaryen.octopus.vo.InterviewerInterviewVo;
import com.targaryen.octopus.vo.ResumeVo;

import java.util.List;

/**
 * Created by Liu Mengyang on 2018/09/05
 */
public interface InterviewerService {
    List<InterviewVo> listInterviewsByInterviewerId(int interviewerId);
    ResumeVo findResumeByApplicationId(int applicationId);
    ResumeVo findResumeByInterviewId(int interviewId);
    int setInterviewerStatus(int interviewerStatus, int interviewId, String comment);
    List<InterviewVo> listUnreplyedInterviewsByInterviewerId(int interviewerId);
    List<InterviewVo> listActiveInterviewsByInterviewerId(int interviewerId);
    List<InterviewerInterviewVo> listInterviewerInterviewsByInterviewerId(int interviewerId);
    int setInterviewResultComment(int interviewId, String comment);
    List<ApplicationResumeVo> findApplicationsByPostId(int postId);
    ApplicationResumeVo findApplicationResumeVoByApplicationId(int applicationId);
    List<ApplicationResumeVo> findApplicationsByPostIdAndStatus(int postId, Integer status);
}
