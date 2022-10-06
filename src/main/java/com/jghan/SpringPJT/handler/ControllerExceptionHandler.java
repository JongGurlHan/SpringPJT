package com.jghan.SpringPJT.handler;

import com.jghan.SpringPJT.util.Script;
import com.jghan.SpringPJT.web.dto.CMResDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public String runtimeException(RuntimeException e){
//        return e.getMessage();
//    }

//    @ExceptionHandler(CustomValidationException.class)
//    public CMResDto<?> customValidationException(CustomValidationException e){
//        System.out.println("===========");
//        System.out.println(e.toString());
//        System.out.println("===========");
//        return new CMResDto<Map<String,String>>( -1, e.getMessage(), e.getErrorMap());
//    }

    @ExceptionHandler(CustomValidationException.class)
    public String customValidationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }

}
