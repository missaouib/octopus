package com.targaryen.octopus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryImpl implements DaoFactory{
    @Autowired
    private ApplicationDtoRepository applicationDtoRepository;
    @Autowired
    private ApplicantDtoRepository applicantDtoRepository;
    @Autowired
    private InterviewDtoRepository interviewDtoRepository;
    @Autowired
    private InterviewerDtoRepository interviewerDtoRepository;
    @Autowired
    private PostDtoRepository postDtoRepository;
    @Autowired
    private RoleDtoRepository roleDtoRepository;
    @Autowired
    private UserDtoRepository userDtoRepository;
    @Autowired
    private DptManagerDtoRepository dptManagerDtoRepository;
    @Autowired
    private HRDtoRepository hrDtoRepository;
    @Autowired
    private ResumeDtoRepository resumeDtoRepository;
    @Autowired
    private ResumeModelDtoRepository resumeModelDtoRepository;
    @Autowired
    private DepartmentDtoRepository departmentDtoRepository;
    @Autowired
    private EducationExperienceRepository educationExperienceRepository;
    @Autowired
    private WorkExperienceRepository workExperienceRepository;


    @Override
    public ApplicationDtoRepository getApplicationDtoRepository() {
        return applicationDtoRepository;
    }

    @Override
    public ApplicantDtoRepository getApplicantDtoRepository() {
        return applicantDtoRepository;
    }

    @Override
    public DptManagerDtoRepository getDptManagerDtoRepository() {
        return dptManagerDtoRepository;
    }

    @Override
    public HRDtoRepository getHRDtoRepository() {
        return hrDtoRepository;
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
    public PostDtoRepository getPostDtoRepository() {
        return postDtoRepository;
    }

    @Override
    public ResumeDtoRepository getResumeDtoRepository() {
        return resumeDtoRepository;
    }

    @Override
    public RoleDtoRepository getRoleDtoRepository() {
        return roleDtoRepository;
    }

    @Override
    public UserDtoRepository getUserDtoRepository() {
        return userDtoRepository;
    }

    @Override
    public ResumeModelDtoRepository getResumeModelDtoRepository() {
        return resumeModelDtoRepository;
    }

    @Override
    public DepartmentDtoRepository getDepartmentDtoRepository() {
        return departmentDtoRepository;
    }

    @Override
    public EducationExperienceRepository getEducationExperienceRepository() {
        return educationExperienceRepository;
    }

    @Override
    public WorkExperienceRepository getWorkExperienceRepository() {
        return workExperienceRepository;
    }
}
