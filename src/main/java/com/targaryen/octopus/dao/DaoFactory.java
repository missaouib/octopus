package com.targaryen.octopus.dao;

public interface DaoFactory {
    NoteRepository getNoteRepository();
    ApplicationDtoRepository getApplicationDtoRepository();
    CandidateDtoRepository getCandidateDtoRepository();
    InterviewDtoRepository getInterviewDtoRepository();
    InterviewerDtoRepository getInterviewerDtoRepository();
    JobDtoRepository getJobDtoRepository();
    RoleDtoRepository getRoleDtoRepository();
    UserDtoRepository getUserDtoRepository();
}
