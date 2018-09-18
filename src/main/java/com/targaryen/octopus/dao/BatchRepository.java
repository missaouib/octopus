package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.BatchDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<BatchDto, Integer> {
    BatchDto findByBatchId(Integer batchId);
}
