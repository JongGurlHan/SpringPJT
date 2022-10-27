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
    private String category;

    @NotBlank
    private String address;

    @NotBlank
    private String url;

    @NotBlank
    private String lat;

    @NotBlank
    private String lng;

    public Matzip toEntity(){
        return Matzip.builder()
                .name(name)
                .category(category)
                .address(address)
                .url(url)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
