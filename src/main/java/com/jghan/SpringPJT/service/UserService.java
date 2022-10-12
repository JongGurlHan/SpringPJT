package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.domain.user.UserRepository;
import com.jghan.SpringPJT.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User update(int id, User user){
        User userEntity = userRepository.findById(id).orElseThrow(()-> { return new CustomValidationApiException("찾을 수 없는 ID입니다.");});

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setPhone(user.getPhone());

        return userEntity;

    }


}
