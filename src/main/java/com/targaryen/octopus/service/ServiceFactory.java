package com.targaryen.octopus.service;

public interface ServiceFactory {
    UserService getUserService();
    DptManagerService getDptManagerService();
    HRService getHRService();
}
