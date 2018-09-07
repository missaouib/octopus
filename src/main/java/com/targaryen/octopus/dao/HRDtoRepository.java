package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.HRDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  @author He Junfeng
 * */
public interface HRDtoRepository extends JpaRepository<HRDto, Integer> {
}
