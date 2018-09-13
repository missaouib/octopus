package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.InterviewDto;
import com.targaryen.octopus.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface InterviewDtoRepository extends JpaRepository<InterviewDto, Integer> {

    List<InterviewDto> findInterviewDtoByInterviewerStatus(int interviewerStatus);
    List<InterviewDto> findInterviewDtoByApplicantStatus(int applicantStatus);
    InterviewDto findInterviewDtoByInterviewId(Integer id);
    void deleteInterviewDtoByInterviewId(int interviewId);

    @Query("select t from InterviewDto t where t.post = :post and t.interviewRound = :interviewRound and t.interviewStartTime >= :startTime and t.interviewStartTime <= :endTime")
    List<InterviewDto> findAllByPostAndRoundAndTime(@Param("post")PostDto post, @Param("interviewRound") Integer interviewRound, @Param("startTime") Date startTime, @Param("endTime") Date entTime);

    @Query("select t from InterviewDto t where t.post = :post and t.interviewRound = :interviewRound")
    List<InterviewDto> findAllByPostAndRound(@Param("post")PostDto post, @Param("interviewRound") Integer interviewRound);
}
