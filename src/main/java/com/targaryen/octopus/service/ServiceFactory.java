package com.targaryen.octopus.service;

public interface ServiceFactory {
    UserService getUserService();
    DptManagerService getDptManagerService();
    HRService getHRService();
    PublicService getPublicService();
    ApplicantService getApplicantService();
    IDService getIDService();
    InterviewerService getInterviewerService();
    FileStorageService getFileStorageService();
    MessageService getMessageService();
    AnnouncementService getAnnouncementService();
}
