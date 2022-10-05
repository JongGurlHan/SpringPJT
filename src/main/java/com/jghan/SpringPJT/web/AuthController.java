package com.jghan.SpringPJT.web;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(){
        return "auth/signup";
    }
}
