package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.domain.board.Board;
import com.jghan.SpringPJT.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "board/boardInput";
    }

    @PostMapping("/board/write")
    public String boardWrite(Board board, Model model, MultipartFile file) throws Exception{
        boardService.write(board, file);
        model.addAttribute("message", "글 작성이 완료됐습니다.");
        model.addAttribute("url", "/board/list");
        return "message/message";
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

    @GetMapping("/board/delete/{id}")
    public String boardDelete(@PathVariable int id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdateForm(@PathVariable int id,
                              Model model){

        model.addAttribute("board", boardService.boardView(id));

        return "board/modify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable int id, Board board, MultipartFile file) throws Exception{

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        return "redirect:/board/list";
    }


}
