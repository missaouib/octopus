package com.targaryen.octopus.service;

import com.targaryen.octopus.controller.HRController;
import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.MessageRepository;
import com.targaryen.octopus.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(HRController.class);

    private MessageRepository messageRepository;
    private SimpMessagingTemplate sender;

    @Autowired
    public MessageServiceImpl(DaoFactory daoFactory, SimpMessagingTemplate sender) {
        this.messageRepository = daoFactory.getMessageRepostiry();
        this.sender = sender;
    }

    @Override
    @Async
    public void broadcastAndSave(String toChannel, MessageDto message, boolean pleaseSave) {
        try {
            if (message != null) {
                // WebSocket STOMP broadcasting
                sender.convertAndSend(toChannel, message);

                if (pleaseSave) {
                    // Save to DB
                    messageRepository.save(message);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
