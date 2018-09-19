package com.targaryen.octopus.dao;

import com.targaryen.octopus.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageDto, Integer> {
    List<MessageDto> findAllByChannelOrderByMessageIdDesc(String channel);

    void save(List<MessageDto> messageDtoList);

    MessageDto findOneByMessageId(int messageId);
}
