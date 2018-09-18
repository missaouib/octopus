package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageDto, Integer> {
}
