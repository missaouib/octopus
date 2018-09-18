package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.AnnouncementDto;
import com.targaryen.octopus.dto.BatchDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementDto, Integer> {
    List<AnnouncementDto> findAllByBatch(BatchDto batchDto);
    AnnouncementDto findByAnnouncementId(Integer announcementId);
}
