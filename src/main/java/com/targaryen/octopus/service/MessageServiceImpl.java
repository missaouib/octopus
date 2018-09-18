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

import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(HRController.class);

    private final String channelPrefix = "/octopus/ws/";
    private final String linkPrefix = "/octopus/";

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
                // Preprocess
                message.setLink(linkPrefix + message.getLink());

                if (pleaseSave) {
                    // Save to DB
                    messageRepository.save(message);
                }

                // Preprocess
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                message.setCreateTimeString(dateFormat.format(message.getCreateTime()));

                // WebSocket STOMP broadcasting
                sender.convertAndSend(channelPrefix + toChannel, message);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
