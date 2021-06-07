package com.company.hoteladministrator.security;

import com.company.hoteladministrator.api.service.IUserService;
import com.company.hoteladministrator.model.User;
import com.company.hoteladministrator.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final IUserService userService;
    private final JwtUserFactory jwtUserFactory;

    @Autowired
    public JwtUserDetailsService(IUserService userService, JwtUserFactory jwtUserFactory) {
        this.userService = userService;
        this.jwtUserFactory = jwtUserFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        return jwtUserFactory.create(user);
    }
}
