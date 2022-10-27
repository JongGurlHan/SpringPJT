package com.jghan.SpringPJT.domain.matzip;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jghan.SpringPJT.domain.user.User;
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
public class Matzip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false) //상호
    private String name;

    @Column                     //음식 카테고리
    private String category;

    @Column(nullable = false)   //주소
    private String address;

    @Column(nullable = false)   //유튜브 경로
    private String url;

    @Column(nullable = false)   //위도
    private String lat;

    @Column(nullable = false)   //경도
    private String lng;

    @JoinColumn(name = "userId") //업로드유저
    @ManyToOne
    private User user;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
