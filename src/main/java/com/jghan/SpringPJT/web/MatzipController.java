package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.domain.matzip.Matzip;
import com.jghan.SpringPJT.service.MatzipService;
import com.jghan.SpringPJT.web.dto.matzip.MatzipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class MatzipController {

    private final MatzipService matzipService;

    @GetMapping("/matzip/input")
    public String matzipForm(){
        return "matzip/input";
    }

    @PostMapping("/matzip/input")
    public String matzipRegist(@Valid MatzipDto matzipDto){
        Matzip matzip = matzipDto.toEntity();
        matzipService.register(matzip);
        System.out.println(matzip);
        return "matzip/input";
    }

}
