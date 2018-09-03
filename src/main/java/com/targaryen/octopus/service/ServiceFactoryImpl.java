package com.targaryen.octopus.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory{
    @Autowired
    private DptManagerService dptManagerService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @Override
    public NoteService getNoteService() {
        return this.noteService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
    
    public DptManagerService getDptManagerService() {
        return dptManagerService;
    }

}
