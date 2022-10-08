package com.jghan.SpringPJT.handler;

import com.jghan.SpringPJT.handler.ex.CustomValidationApiException;
import com.jghan.SpringPJT.handler.ex.CustomValidationException;
import com.jghan.SpringPJT.util.Script;
import com.jghan.SpringPJT.web.dto.CMResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String customValidationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> customValidationApiException(CustomValidationApiException e){
        return new  ResponseEntity<>(new CMResDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

}
