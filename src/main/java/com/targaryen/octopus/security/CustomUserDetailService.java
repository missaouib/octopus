package com.targaryen.octopus.security;

import com.targaryen.octopus.dao.UserDtoRepository;
import com.targaryen.octopus.service.ServiceFactory;
import com.targaryen.octopus.service.UserService;
import com.targaryen.octopus.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomUserDetailService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public CustomUserDetailService(ServiceFactory serviceFactory) {
        this.userService = serviceFactory.getUserService();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        UserVo user = userService.getUserByUserName(userName);
        Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(user.getUserRole());

        return new CustomUserDetails(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                authorities
        );
    }
}

