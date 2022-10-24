package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.matzip.Matzip;
import com.jghan.SpringPJT.domain.matzip.MatzipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MatzipService {

    private final MatzipRepository matzipRepository;

    @Transactional
    public Matzip register(Matzip matzip){
        Matzip matzipEntity = matzipRepository.save(matzip);
        return matzipEntity;
    }

}
