package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController { //추후 이름변경

    @GetMapping("/")
    public String main(@AuthenticationPrincipal PrincipalDetails principalDetails,
                        Model model){

        model.addAttribute("user", principalDetails.getUser());

        return "main/main";
    }
    @GetMapping("/main/test")
    public String test(){
        return "main/test";
    }





}
