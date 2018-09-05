package com.targaryen.octopus.service;

import com.targaryen.octopus.dao.ApplicantDtoRepository;
import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.ResumeDtoRepository;
import com.targaryen.octopus.dao.UserDtoRepository;
import com.targaryen.octopus.dto.ApplicantDto;
import com.targaryen.octopus.dto.ApplicationDto;
import com.targaryen.octopus.dto.ResumeDto;
import com.targaryen.octopus.dto.UserDto;
import com.targaryen.octopus.util.StatusCode;
import com.targaryen.octopus.vo.ApplicationVo;
import com.targaryen.octopus.vo.ResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    private ApplicantDtoRepository applicantDtoRepository;
    private UserDtoRepository userDtoRepository;
    private ResumeDtoRepository resumeDtoRepository;

    @Autowired
    public ApplicantServiceImpl(DaoFactory daoFactory) {
        this.applicantDtoRepository = daoFactory.getApplicantDtoRepository();
        this.userDtoRepository = daoFactory.getUserDtoRepository();
        this.resumeDtoRepository = daoFactory.getResumeDtoRepository();
    }

    public int CreateResume(int userId) {
        UserDto userDto;
        ApplicantDto applicantDto;
        ResumeDto resumeDto = new ResumeDto();
        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            applicantDto = userDto.getApplicant();
            resumeDto.setApplicant(applicantDto);
            resumeDtoRepository.save(resumeDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    public int SaveResume(int userId, ResumeVo resumeVo) {
        ResumeDto resumeDto;
        UserDto userDto;

        if(findResumeByUserId(userId) == null) {
            if(CreateResume(userId) != StatusCode.SUCCESS)
                return StatusCode.FAILURE;
        }

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            resumeDto = userDto.getApplicant().getResume();
            resumeDto.setApplicantAge(resumeVo.getApplicantAge());
            resumeDto.setApplicantCity(resumeVo.getApplicantCity());
            resumeDto.setApplicantCV(resumeVo.getApplicantCV());
            resumeDto.setApplicantDegree(resumeVo.getApplicantDegree());
            resumeDto.setApplicantEmail(resumeVo.getApplicantEmail());
            resumeDto.setApplicantMajor(resumeVo.getApplicantMajor());
            resumeDto.setApplicantName(resumeVo.getApplicantName());
            resumeDto.setApplicantPhone(resumeVo.getApplicantPhone());
            resumeDto.setApplicantSchool(resumeVo.getApplicantSchool());
            resumeDto.setApplicantSex(resumeVo.getApplicantSex());
            resumeDtoRepository.save(resumeDto);
        } catch (DataAccessException e) {
            return StatusCode.FAILURE;
        }

        return StatusCode.SUCCESS;
    }

    public ResumeVo findResumeByUserId(int userId) {
        ApplicantDto applicantDto;
        UserDto userDto;
        ResumeDto resumeDto;

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            applicantDto = userDto.getApplicant();
            if(applicantDto == null)
                return null;
            resumeDto = applicantDto.getResume();
            if(resumeDto == null)
                return null;
        } catch (DataAccessException e) {
            return null;
        }

        return new ResumeVo.Builder()
                .applicantAge(resumeDto.getApplicantAge())
                .applicantCity(resumeDto.getApplicantCity())
                .applicantCV(resumeDto.getApplicantCV())
                .applicantDegree(resumeDto.getApplicantDegree())
                .applicantEmail(resumeDto.getApplicantEmail())
                .applicantMajor(resumeDto.getApplicantMajor())
                .applicantName(resumeDto.getApplicantName())
                .applicantPhone(resumeDto.getApplicantPhone())
                .applicantSchool(resumeDto.getApplicantSchool())
                .applicantSex(resumeDto.getApplicantSex())
                .resumeId(resumeDto.getResumeId()).build();
    }

    public List<ApplicationVo> findApplicationsByUserId(int userId) {
        UserDto userDto;
        ApplicantDto applicantDto;
        List<ApplicationDto> applicationDtos;
        List<ApplicationVo> applicationVos = new ArrayList<>();

        try {
            userDto = userDtoRepository.findUserDtoByUserId(userId);
            if(userDto == null)
                return new ArrayList<>();
            applicantDto = userDto.getApplicant();
            if(applicantDto == null)
                return new ArrayList<>();
            applicationDtos = applicantDto.getApplications();
            for(ApplicationDto a: applicationDtos) {
                applicationVos.add(
                        new ApplicationVo.Builder()
                        .applicantId(a.getApplicationId())
                        .applicationId(a.getApplicationId())
                        .postId(a.getApplicationId())
                        .status(a.getStatus()).build()
                );
            }
        } catch (DataAccessException e) {
            return new ArrayList<>();
        }

        return applicationVos;
    }


}
