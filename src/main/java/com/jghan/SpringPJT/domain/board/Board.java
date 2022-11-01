package com.jghan.SpringPJT.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String content;

    private String filename;
    private String filepath;

    private LocalDateTime createDate;

    @PrePersist //db에 INSERT되기전에 실행
    public void createData() {
        this.createDate = LocalDateTime.now();
    }
}
