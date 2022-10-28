package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.domain.board.Board;
import com.jghan.SpringPJT.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "board/board";
    }

    @PostMapping("/board/write")
    public String boardWrite(Board board){
        boardService.write(board);
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list", boardService.boardList());
        return "board/boardlist";
    }

    @GetMapping("/board/view/{id}")
    public String boardView(Model model, @PathVariable int id){

        model.addAttribute("board", boardService.boardView(id));

        return "board/boardView";
    }

}
