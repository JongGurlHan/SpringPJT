package com.jghan.SpringPJT.web.dto.auth;

import com.jghan.SpringPJT.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @Size(min=2, max =20)
    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
