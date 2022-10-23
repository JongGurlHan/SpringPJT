package com.jghan.SpringPJT.handler;

import com.jghan.SpringPJT.handler.ex.CustomApiException;
import com.jghan.SpringPJT.handler.ex.CustomException;
import com.jghan.SpringPJT.handler.ex.CustomValidationApiException;
import com.jghan.SpringPJT.handler.ex.CustomValidationException;
import com.jghan.SpringPJT.util.Script;
import com.jghan.SpringPJT.web.dto.CMResDto;
import com.jghan.SpringPJT.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    //  <Map<String, String> => <?> 로 해도 된다 -> 추론가능!
    @ExceptionHandler(CustomValidationException.class) //모든 runtimeException을 가로챔
    public String  validationException(CustomValidationException e){
        if(e.getErrorMap() == null){
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }
    }
    //1) 유저조회했는데 없을경우
    @ExceptionHandler(CustomException.class) //모든 runtimeException을 가로챔
    public String Exception(CustomException e){
        return Script.back(e.getMessage());

    }

    @ExceptionHandler(CustomValidationApiException.class) //모든 runtimeException을 가로챔
    public ResponseEntity<? >  validationApiException(CustomValidationApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1 , e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class) //모든 runtimeException을 가로챔
    public ResponseEntity<? > apiException(CustomApiException e){
        return new ResponseEntity<>(new CMRespDto<>(-1 , e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}
