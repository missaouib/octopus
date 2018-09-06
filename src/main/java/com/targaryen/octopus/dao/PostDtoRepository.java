package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface PostDtoRepository extends JpaRepository<PostDto, Integer> {
    void deletePostDtoByPostId(Integer id);

    PostDto findPostDtoByPostId(Integer id);

    List<PostDto> findAllByStatusOrderByPostIdDesc(Integer status);

    List<PostDto> findAllByStatusNotOrderByPostIdDesc(Integer status);
}
