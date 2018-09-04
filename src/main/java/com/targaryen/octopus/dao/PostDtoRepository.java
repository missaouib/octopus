package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface PostDtoRepository extends JpaRepository<PostDto, Integer> {
    public void deletePostDtoByPostId(Integer id);

    public PostDto findPostDtoByPostId(Integer id);

    public List<PostDto> findPostDtoByStatus(Integer status);

    @Query("select t from PostDto t where t.status = 1")
    public List<PostDto> findPublishPosts();

    @Query("select t from PostDto t where t.status > -1")
    public List<PostDto> findUnrevokedPosts();
}
