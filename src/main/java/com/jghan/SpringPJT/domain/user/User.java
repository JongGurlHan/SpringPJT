package com.jghan.SpringPJT.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jghan.SpringPJT.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String email;

    @Column(length = 10, unique = true) //Oauth2로그인을 위해 컬럼 늘리기
    private String username;

    @Column(nullable = false)
    private String password;

    private String profileImageUrl;
    private String role;
    private String phone;


    private LocalDateTime createDate;


    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }


}
