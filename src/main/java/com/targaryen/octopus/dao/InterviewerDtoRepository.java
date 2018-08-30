package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.InterviewerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface InterviewerDtoRepository extends JpaRepository<InterviewerDto, Integer> {
}
