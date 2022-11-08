package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.handler.ex.CustomValidationException;
import com.jghan.SpringPJT.service.AuthService;
import com.jghan.SpringPJT.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){

        User user = signupDto.toEntity();
        authService.register(user);
        return "auth/signin";

    }
}
