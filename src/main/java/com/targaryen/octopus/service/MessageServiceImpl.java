package com.targaryen.octopus.service;

import com.targaryen.octopus.controller.HRController;
import com.targaryen.octopus.dao.DaoFactory;
import com.targaryen.octopus.dao.MessageRepository;
import com.targaryen.octopus.dto.MessageDto;
import com.targaryen.octopus.util.status.HRMessage;
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

                // Set icon and color
                if (toChannel.equals("hr")) {
                    setHRStyle(message);
                }

                // WebSocket STOMP broadcasting
                sender.convertAndSend(channelPrefix + toChannel, message);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void setHRStyle(MessageDto message) {
        switch (message.getMessageType()) {
            case HRMessage.DPT_CREATE_POST:
                message.setIconName("edit");
                message.setIconColor("navy");
                break;
            case HRMessage.DPT_UPDATE_POST:
                message.setIconName("edit");
                message.setIconColor("navy");
                break;
            case HRMessage.APP_APPLY_POST:
                message.setIconName("inbox");
                message.setIconColor("blue");
                break;
            case HRMessage.DPT_PASS_APPS:
                message.setIconName("eye");
                message.setIconColor("yellow");
                break;
            case HRMessage.DPT_REJECT_APPS:
                message.setIconName("eye");
                message.setIconColor("yellow");
                break;
            case HRMessage.APP_ACCEPT_INTERVIEW:
                message.setIconName("calendar-plus-o");
                message.setIconColor("black");
                break;
            case HRMessage.APP_REJECT_INTERVIEW:
                message.setIconName("calendar-plus-o");
                message.setIconColor("black");
                break;
            case HRMessage.WER_ACCEPT_INTERVIEW:
                message.setIconName("calendar-plus-o");
                message.setIconColor("black");
                break;
            case HRMessage.WER_REJECT_INTERVIEW:
                message.setIconName("calendar-plus-o");
                message.setIconColor("black");
                break;
            case HRMessage.WER_COMMENT_INTERVIEW:
                message.setIconName("commenting");
                message.setIconColor("red");
                break;
        }
    }
}
