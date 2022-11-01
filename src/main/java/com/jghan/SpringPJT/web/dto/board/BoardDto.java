package com.jghan.SpringPJT.web.dto.board;

import com.jghan.SpringPJT.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

//팔로우 정보를 보는 dto
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {

    private String title;
    private String content;

//    private MultipartFile file;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
