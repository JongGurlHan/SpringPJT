package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.board.Board;
import com.jghan.SpringPJT.domain.board.BoardRepository;
import com.jghan.SpringPJT.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //게시글 작성
    public void write(Board board){
        boardRepository.save(board);
    }

    // 게시글 리스트 조회
    public List<Board> boardList(){
        return boardRepository.findAll();
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
