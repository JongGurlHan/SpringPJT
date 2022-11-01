package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.board.Board;
import com.jghan.SpringPJT.domain.board.BoardRepository;
import com.jghan.SpringPJT.domain.user.User;
import com.jghan.SpringPJT.domain.user.UserRepository;
import com.jghan.SpringPJT.handler.ex.CustomApiException;
import com.jghan.SpringPJT.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Value("${file.path}")
    private String uploadFolder;

    //게시글 작성
    public void write(Board board, int principalId){

        User userEntity = userRepository.findById(principalId).orElseThrow(()->{
            throw new CustomApiException("유저아이디를 찾을수 없습니다.");
        });

        Board boardEntity = new Board();
        boardEntity.setTitle(board.getTitle());
        boardEntity.setContent(boardEntity.getContent());
        boardEntity.setUser(userEntity);

        boardRepository.save(boardEntity);
    }

    // 게시글 리스트 조회
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable){
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }



    // 특정 게시글 불러오기
    public Board boardView(int id){
        return boardRepository.findById(id).orElseThrow(()->{
            throw new CustomException("해당 게시글을 찾을 수 없습니다.");
        });
    }

    public void boardDelete(int id){
        boardRepository.deleteById(id);
    }


}
