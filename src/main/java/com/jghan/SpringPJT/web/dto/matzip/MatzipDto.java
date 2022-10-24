package com.jghan.SpringPJT.web.dto.matzip;

import com.jghan.SpringPJT.domain.matzip.Matzip;
import com.jghan.SpringPJT.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatzipDto {

    @Size(min=1, max =100)
    @NotBlank
    private String name;

    @NotBlank
    private String lat  ;

    @NotBlank
    private String lng;

    @NotBlank
    private String comment;


    public Matzip toEntity(){
        return Matzip.builder()
                .name(name)
                .lat(lat)
                .lng(lng)
                .comment(comment)
                .build();
    }
}
