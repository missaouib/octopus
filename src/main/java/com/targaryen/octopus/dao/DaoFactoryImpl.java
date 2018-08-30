package com.targaryen.octopus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryImpl implements DaoFactory{
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private ApplicationDtoRepository applicationDtoRepository;
    @Autowired
    private CandidateDtoRepository candidateDtoRepository;
    @Autowired
    private InterviewDtoRepository interviewDtoRepository;
    @Autowired
    private InterviewerDtoRepository interviewerDtoRepository;
    @Autowired
    private JobDtoRepository jobDtoRepository;
    @Autowired
    private RoleDtoRepository roleDtoRepository;
    @Autowired
    private UserDtoRepository userDtoRepository;


    @Override
    public NoteRepository getNoteRepository() {
        return noteRepository;
    }

    @Override
    public ApplicationDtoRepository getApplicationDtoRepository() {
        return applicationDtoRepository;
    }

    @Override
    public CandidateDtoRepository getCandidateDtoRepository() {
        return candidateDtoRepository;
    }

    @Override
    public InterviewDtoRepository getInterviewDtoRepository() {
        return interviewDtoRepository;
    }

    @Override
    public InterviewerDtoRepository getInterviewerDtoRepository() {
        return interviewerDtoRepository;
    }

    @Override
    public JobDtoRepository getJobDtoRepository() {
        return jobDtoRepository;
    }

    @Override
    public RoleDtoRepository getRoleDtoRepository() {
        return roleDtoRepository;
    }

    @Override
    public UserDtoRepository getUserDtoRepository() {
        return userDtoRepository;
    }
}
