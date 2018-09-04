package com.targaryen.octopus.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;

public class CustomUserDetails extends User {
    private int userId;
    CustomUserDetails(int userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
