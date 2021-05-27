package com.company.hoteladministrator.security.jwt;

import com.company.hoteladministrator.model.Role;
import com.company.hoteladministrator.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public JwtUser create(User user) {
        return new JwtUser(user.getId(),
                           user.getUsername(),
                           user.getPassword(),
                           mapToGrantedAuthorities(user.getRole()));
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
