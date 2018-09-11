package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.WorkExperienceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceDto, Integer> {
}
