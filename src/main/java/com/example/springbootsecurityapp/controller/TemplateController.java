package com.example.springbootsecurityapp.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Profile("form-auth")
public class TemplateController {
    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/login/success")
    public String getSuccessLoginView() {
        return "successful-login";
    }
}
