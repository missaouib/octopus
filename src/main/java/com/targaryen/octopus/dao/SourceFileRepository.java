package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.SourceFileDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceFileRepository extends JpaRepository<SourceFileDto, Integer> {
}
