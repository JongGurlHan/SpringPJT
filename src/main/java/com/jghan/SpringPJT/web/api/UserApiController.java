package com.jghan.SpringPJT.web.api;

import com.jghan.SpringPJT.config.auth.PrincipalDetails;
import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.handler.ex.CustomValidationApiException;
import com.jghan.SpringPJT.service.UserService;
import com.jghan.SpringPJT.web.dto.CMResDto;
import com.jghan.SpringPJT.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMResDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);

        }else {
            User userEntity = userService.update(id, userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);

            return new CMResDto<>(1, "회원수정완료",userEntity );
        }


    }
}
