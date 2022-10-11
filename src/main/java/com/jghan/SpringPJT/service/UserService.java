package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.domain.user.UserRepository;
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
        userRepository.findById(userRepository.findById(id).get();

        return null;

    }


}
