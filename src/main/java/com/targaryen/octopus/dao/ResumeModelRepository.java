package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ResumeModelDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeModelRepository extends JpaRepository<ResumeModelDto, Integer> {
}
