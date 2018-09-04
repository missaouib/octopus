package com.targaryen.octopus.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthInfo {
    public static int getUserId() {
        return ((CustomUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUserId();
    }

    public static String getUserName() {
        return ((CustomUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()).getUsername();
    }
}
