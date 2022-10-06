package com.jghan.SpringPJT.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMResDto<T> {

    private int code;
    private String message;
    private T data;

}
