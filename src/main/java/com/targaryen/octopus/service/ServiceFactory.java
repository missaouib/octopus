package com.targaryen.octopus.service;

public interface ServiceFactory {
    NoteService getNoteService();
    UserService getUserService();
}
