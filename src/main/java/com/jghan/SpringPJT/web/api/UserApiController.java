package com.jghan.SpringPJT.web.api;

import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.service.UserService;
import com.jghan.SpringPJT.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public int update(@PathVariable int id,
                      @Valid UserUpdateDto userUpdateDto,
                      BindingResult bindingResult,
                      AuthenticationPrincipal authenticationPrincipal){

        User userEntity = userUpdateDto.toEntity();
//        userService.update(userEntity);



    }
}
