package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.JobDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface JobDtoRepository extends JpaRepository<JobDto, Integer> {
}
