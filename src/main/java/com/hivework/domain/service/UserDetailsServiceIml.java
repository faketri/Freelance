package com.hivework.domain.service;

import com.hivework.domain.entity.user.Users;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceIml implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceIml(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return generateUserDetails(userService.findByLogin(username));
    }

    public UserDetails generateUserDetails(Users users) {
        return new org.springframework.security.core.userdetails.User(
                users.getLogin(),
                users.getPassword(),
                users.getRoles()
                        .stream()
                        .map(x -> new SimpleGrantedAuthority(x.name()))
                        .toList()
        );
    }

    public UserService getUserService() {
        return userService;
    }
}
