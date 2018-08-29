package com.targaryen.octopus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory{
    private final NoteService noteService;

    @Autowired
    public ServiceFactoryImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public NoteService getNoteService() {
        return this.noteService;
    }
}
