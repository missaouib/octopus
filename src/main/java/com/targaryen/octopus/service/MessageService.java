package com.targaryen.octopus.service;

import com.targaryen.octopus.dto.MessageDto;

public interface MessageService {
    void broadcastAndSave(String toChannel, MessageDto message, boolean pleaseSave);
}
