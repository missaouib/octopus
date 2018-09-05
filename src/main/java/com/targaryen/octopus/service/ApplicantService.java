package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.ResumeVo;

public interface ApplicantService {
    int SaveResume(int userId, ResumeVo resumeVo);
    ResumeVo findResumeByUserId(int userId);
}
