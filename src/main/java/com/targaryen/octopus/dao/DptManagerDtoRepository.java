package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.DptManagerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author He Junfeng
 * */
@Repository
public interface DptManagerDtoRepository extends JpaRepository<DptManagerDto, Integer> {
    DptManagerDto findDptManagerDtoByDptManagerId(Integer id);
}
