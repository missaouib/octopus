package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.DepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDtoRepository extends JpaRepository<DepartmentDto, Integer> {
}
