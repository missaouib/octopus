package com.targaryen.octopus.service;

import com.targaryen.octopus.dto.MessageDto;

import java.util.List;

public interface MessageService {
    void broadcastAndSave(String toChannel, MessageDto message, boolean pleaseSave);

    List<MessageDto> viewHrAll();

    void batchMarkAsRead(int[] messageIds);

    void batchMarkAsUnread(int[] messageIds);

    void markOneAsRead(int messageId);

    void markOneAsStarred(int messageId);

    void markOneAsUnstarred(int messageId);

}
