package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ApplicantDto;
import com.targaryen.octopus.dto.ApplicationDto;
import com.targaryen.octopus.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface ApplicationDtoRepository extends JpaRepository<ApplicationDto, Integer> {

    ApplicationDto findApplicationDtoByApplicationId(Integer id);

    List<ApplicationDto> findByApplicantAndPost(ApplicantDto applicantDto, PostDto postDto);

    List<ApplicationDto> findAllByStatusOrderByApplicationIdDesc(Integer status);
}
