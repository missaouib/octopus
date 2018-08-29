package com.targaryen.octopus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoFactoryImpl implements DaoFactory{
    private final NoteDao noteDao;

    @Autowired
    public DaoFactoryImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public NoteDao getNoteDao(){
        return this.noteDao;
    }
}
