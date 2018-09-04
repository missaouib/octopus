package com.targaryen.octopus.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceFactoryImpl implements ServiceFactory{
    @Autowired
    private DptManagerService dptManagerService;
    @Autowired
    private UserService userService;
    @Autowired
    private HRService hrService;

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

}
