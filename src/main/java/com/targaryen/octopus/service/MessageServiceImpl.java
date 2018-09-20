package com.targaryen.octopus.service;

import com.targaryen.octopus.config.ContextProperties;
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
import java.util.List;
import java.util.Locale;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    ContextProperties contextProperties;

    private Logger logger = LoggerFactory.getLogger(HRController.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

    private final String channelPrefix = "/octopus/ws/";

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
                // Pre-process link
                message.setLink("/" + message.getLink());

                // Save to DB
                if (pleaseSave) {
                    messageRepository.save(message);
                }

                // Pre-process create time string
                message.setCreateTimeString(dateFormat.format(message.getCreateTime()));

                // Pre-process icon and color
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

    @Override
    public List<MessageDto> viewHrAll() {
        List<MessageDto> hrMessages = messageRepository.findAllByChannelOrderByMessageIdDesc("hr");
        hrMessages.forEach(messageDto -> {
            // Pre-process
            setHRStyle(messageDto);
            messageDto.setCreateTimeString(dateFormat.format(messageDto.getCreateTime()));
        });
        return hrMessages;
    }

    @Override
    public void batchMarkAsRead(int[] messageIds) {
        for (int i = 0; i != messageIds.length; i++) {
            MessageDto messageDto = messageRepository.findOneByMessageId(messageIds[i]);
            messageDto.setRead(true);
            messageRepository.save(messageDto);
        }
    }

    @Override
    public void batchMarkAsUnread(int[] messageIds) {
        for (int i = 0; i != messageIds.length; i++) {
            MessageDto messageDto = messageRepository.findOneByMessageId(messageIds[i]);
            messageDto.setRead(false);
            messageRepository.save(messageDto);
        }
    }

    @Override
    public void markOneAsRead(int messageId) {
        MessageDto messageDto = messageRepository.findOneByMessageId(messageId);
        messageDto.setRead(true);
        messageRepository.save(messageDto);
    }

    @Override
    public void markOneAsStarred(int messageId) {
        MessageDto messageDto = messageRepository.findOneByMessageId(messageId);
        messageDto.setStarred(true);
        messageRepository.save(messageDto);
    }

    @Override
    public void markOneAsUnstarred(int messageId) {
        MessageDto messageDto = messageRepository.findOneByMessageId(messageId);
        messageDto.setStarred(false);
        messageRepository.save(messageDto);
    }


    /****************************************/

    /* Set icon and color displayed on web page */
    private void setHRStyle(MessageDto message) {
        switch (message.getMessageType()) {
            case HRMessage.DPT_CREATE_POST:
            case HRMessage.DPT_UPDATE_POST:
                message.setIconName("edit");
                message.setIconColor("navy");
                break;
            case HRMessage.APP_APPLY_POST:
                message.setIconName("inbox");
                message.setIconColor("blue");
                break;
            case HRMessage.DPT_PASS_APPS:
            case HRMessage.DPT_REJECT_APPS:
                message.setIconName("eye");
                message.setIconColor("yellow");
                break;
            case HRMessage.APP_ACCEPT_INTERVIEW:
            case HRMessage.APP_REJECT_INTERVIEW:
            case HRMessage.WER_ACCEPT_INTERVIEW:
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
