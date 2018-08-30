package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.CandidateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface CandidateDtoRepository extends JpaRepository<CandidateDto, Integer> {
}
