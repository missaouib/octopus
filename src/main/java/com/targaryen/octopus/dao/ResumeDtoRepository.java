package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ResumeDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  @author He Junfeng
 * */
public interface ResumeDtoRepository extends JpaRepository<ResumeDto, Integer> {
}
