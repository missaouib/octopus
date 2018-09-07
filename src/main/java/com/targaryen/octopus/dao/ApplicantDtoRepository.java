package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ApplicantDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface ApplicantDtoRepository extends JpaRepository<ApplicantDto, Integer> {
    ApplicantDto findApplicantDtoByApplicantId(Integer applicantId);
}
