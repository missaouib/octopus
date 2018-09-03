package com.targaryen.octopus.dao;

public interface DaoFactory {
    NoteRepository getNoteRepository();
    ApplicationDtoRepository getApplicationDtoRepository();
    ApplicantDtoRepository getApplicantDtoRepository();
    DptManagerDtoRepository getDptManagerDtoRepository();
    HRDtoRepository getHRDtoRepository();
    InterviewDtoRepository getInterviewDtoRepository();
    InterviewerDtoRepository getInterviewerDtoRepository();
    PostDtoRepository getPostDtoRepository();
    ResumeDtoRepository getResumeDtoRepository();
    RoleDtoRepository getRoleDtoRepository();
    UserDtoRepository getUserDtoRepository();
}
