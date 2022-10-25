package com.jghan.SpringPJT.web.api;

import com.jghan.SpringPJT.domain.matzip.Matzip;
import com.jghan.SpringPJT.service.MatzipService;
import com.jghan.SpringPJT.web.dto.CMResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatzipApiController {

    private final MatzipService matzipService;

    @GetMapping("/api/matzip")
    public ResponseEntity<?>matzipList(){
        List<Matzip> matzipList = matzipService.matzipList();
        return new ResponseEntity<>(new CMResDto<>(1, "리스트조회 성공", matzipList), HttpStatus.OK);
    }
}
