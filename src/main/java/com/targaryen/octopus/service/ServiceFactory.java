package com.targaryen.octopus.service;

public interface ServiceFactory {
    UserService getUserService();
    DptManagerService getDptManagerService();
    HRService getHRService();
    PublicService getPulicService();
    ApplicantService getApplicantService();
    IDService getIDService();
    InterviewerService getInterviewerService();
    FileStorageService getFileStorageService();
}
