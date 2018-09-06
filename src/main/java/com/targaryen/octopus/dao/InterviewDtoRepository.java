package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.InterviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface InterviewDtoRepository extends JpaRepository<InterviewDto, Integer> {
    public InterviewDto findInterviewDtoByInterviewId(Integer id);

    public void deleteInterviewDtoByInterviewId(Integer id);
}
