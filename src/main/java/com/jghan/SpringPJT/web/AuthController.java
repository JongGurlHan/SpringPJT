package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.handler.CustomValidationException;
import com.jghan.SpringPJT.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GeneratorType;
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
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        }else{
            User user = signupDto.toEntity();
            authService.register(user);
            return "auth/signup";
        }
    }
}
