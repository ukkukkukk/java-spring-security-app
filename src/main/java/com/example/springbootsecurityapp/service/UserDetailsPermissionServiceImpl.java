package com.example.springbootsecurityapp.service;

import com.example.springbootsecurityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Qualifier("UserDetailsPermissionServiceDb")
public class UserDetailsPermissionServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final static String ROLE_AUTHORITY_PREFIX = "ROLE_";

    @Autowired
    public UserDetailsPermissionServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(s);

        if (user == null)
            throw new UsernameNotFoundException(format("Username [{%s}] not found", s));

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.addAll(user.getUserRole().getUserPermissions()
                .stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getUserPermission()))
                .collect(Collectors.toList())
        );

        authorities.add(new SimpleGrantedAuthority(ROLE_AUTHORITY_PREFIX + user.getUserRole().name()));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
