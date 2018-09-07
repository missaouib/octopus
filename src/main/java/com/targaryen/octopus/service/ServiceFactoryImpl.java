package com.targaryen.octopus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory{
    @Autowired
    private DptManagerService dptManagerService;
    @Autowired
    private UserService userService;
    @Autowired
    private HRService hrService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private IDService idService;
    @Autowired
    private InterviewerService interviewerService;

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public DptManagerService getDptManagerService() {
        return dptManagerService;
    }

    @Override
    public HRService getHRService() {
        return hrService;
    }

    @Override
    public PublicService getPulicService() {
        return publicService;
    }

    @Override
    public ApplicantService getApplicantService() {
        return applicantService;
    }

    @Override
    public IDService getIDService() {
        return idService;
    }

    @Override
    public InterviewerService getInterviewerService() {
        return interviewerService;
    }
}
