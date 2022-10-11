package com.jghan.SpringPJT.web.dto.user;

import com.jghan.SpringPJT.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto {

    @Size(min=2, max =20)
    @NotBlank
    private String username; //필수

    @NotBlank
    private String password; //필수

    @NotBlank
    private String email;


    private String phone;

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .phone(phone)
                .build();
    }


}
