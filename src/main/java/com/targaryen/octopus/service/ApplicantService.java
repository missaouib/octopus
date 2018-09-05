package com.targaryen.octopus.service;

import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.ResumeVo;

import java.util.List;

public interface ApplicantService {
    int SaveResume(int userId, ResumeVo resumeVo);
    ResumeVo findResumeByUserId(int userId);
    List<ApplicationVo> findApplicationsByUserId(int userId);
}
