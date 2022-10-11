package com.jghan.SpringPJT.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController { //추후 이름변경

    @GetMapping({"/", "/image/story"})
    public String story(){
        return "image/story";
    }

}
