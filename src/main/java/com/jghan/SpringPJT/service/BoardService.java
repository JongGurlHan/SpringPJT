package com.jghan.SpringPJT.service;

import com.jghan.SpringPJT.domain.board.Board;
import com.jghan.SpringPJT.domain.board.BoardRepository;
import com.jghan.SpringPJT.handler.ex.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${file.path}")
    private String uploadFolder;

    //게시글 작성
    public void write(Board board, MultipartFile file) throws Exception{


        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(uploadFolder, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath(uploadFolder+fileName);

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
