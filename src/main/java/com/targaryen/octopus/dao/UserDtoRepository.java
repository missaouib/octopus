package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Liu Mengyang
 * */
@Repository
public interface UserDtoRepository extends JpaRepository<UserDto, Integer> {
}
