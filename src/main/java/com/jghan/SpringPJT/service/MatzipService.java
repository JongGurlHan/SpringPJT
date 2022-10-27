package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.matzip.Matzip;
import com.jghan.SpringPJT.domain.matzip.MatzipRepository;
import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.domain.user.UserRepository;
import com.jghan.SpringPJT.handler.ex.CustomApiException;
import com.jghan.SpringPJT.web.dto.matzip.MatzipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatzipService {

    private final MatzipRepository matzipRepository;
    private final UserRepository userRepository;

    @Transactional
    public Matzip register(Matzip matzip){

//        User userEntity = userRepository.findById(userId).orElseThrow(()->{
//            throw new CustomApiException("유저아이디를 찾을수 없습니다.");
//        });
//
//        Matzip matzipEntity = new Matzip();
//        matzipEntity.setName(matzip.getName());
//        matzipEntity.setCategory(matzip.getCategory());
//        matzipEntity.setAddress(matzip.getAddress());
//        matzipEntity.setUrl(matzipEntity.getUrl());
//        matzipEntity.setLat(matzipEntity.getLat());
//        matzipEntity.setLng(matzipEntity.getLng());
//        matzipEntity.setUser(userEntity);

        return  matzipRepository.save(matzip);
    }

//
//    String name, String category, String address,
//    String url, String lat, String lng,
    @Transactional
    public List<Matzip> matzipList(){
        return matzipRepository.findAll();
    }



}
