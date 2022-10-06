package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User register(User user){
        String rawPw = user.getPassword();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        user.setPassword(encPw);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);
        return userEntity;


    }


}
