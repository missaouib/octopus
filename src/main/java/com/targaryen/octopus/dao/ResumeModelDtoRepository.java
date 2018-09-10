package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ResumeModelDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeModelDtoRepository extends JpaRepository<ResumeModelDto, Integer> {
    ResumeModelDto findResumeModelDtoByResumeModelId(Integer id);
}
