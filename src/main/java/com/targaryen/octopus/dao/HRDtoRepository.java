package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.HRDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author He Junfeng
 * */
@Repository
public interface HRDtoRepository extends JpaRepository<HRDto, Integer> {
}
