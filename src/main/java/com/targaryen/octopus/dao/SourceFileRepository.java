package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.SourceFileDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SourceFileRepository extends JpaRepository<SourceFileDto, Integer> {
    List<SourceFileDto> findAllBySourceType(int sourceType);
}
