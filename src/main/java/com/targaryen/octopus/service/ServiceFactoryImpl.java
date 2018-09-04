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
    @Autowired
    private HRService hrService;
    @Autowired
    private PublicService publicService;


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

    @Override
    public HRService getHRService() {
        return hrService;
    }

    @Override
    public PublicService getPulicService() {
        return publicService;
    }


}
