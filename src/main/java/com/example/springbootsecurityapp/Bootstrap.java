package com.example.springbootsecurityapp;

import com.example.springbootsecurityapp.model.User;
import com.example.springbootsecurityapp.security.UserRole;
import com.example.springbootsecurityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ApplicationReadyEvent> {
    private final UserService userService;

    @Autowired
    public Bootstrap(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        createUserWithRole("admin", "admin", UserRole.ADMIN);
        createUserWithRole("student", "student", UserRole.STUDENT);
    }

    private void createUserWithRole(String username, String password, UserRole userRole) {
        User user = User.builder()
                .username(username)
                .password(password)
                .userRole(userRole)
                .build();

        userService.createUser(user);
    }
}
