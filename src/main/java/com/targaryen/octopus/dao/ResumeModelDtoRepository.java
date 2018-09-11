package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ResumeModelDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeModelDtoRepository extends JpaRepository<ResumeModelDto, Integer> {
    ResumeModelDto findResumeModelDtoByResumeModelId(Integer id);
}
