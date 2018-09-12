package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.ResumeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author He Junfeng
 * */
@Repository
public interface ResumeDtoRepository extends JpaRepository<ResumeDto, Integer> {
    ResumeDto findResumeDtoByResumeId(int resumeId);
}
