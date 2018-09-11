package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.EducationExperienceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationExperienceRepository extends JpaRepository<EducationExperienceDto, Integer> {
}
