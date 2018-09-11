package com.targaryen.octopus.dao;

public interface DaoFactory {
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
    ResumeModelDtoRepository getResumeModelDtoRepository();
    DepartmentDtoRepository getDepartmentDtoRepository();
    EducationExperienceRepository getEducationExperienceRepository();
    WorkExperienceRepository getWorkExperienceRepository();
}
