package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ApplicationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface ApplicationDtoRepository extends JpaRepository<ApplicationDto, Integer> {

    ApplicationDto findApplicationDtoByApplicationId(Integer id);

    List<ApplicationDto> findAllByStatusOrderByApplicationIdDesc(Integer status);
}
