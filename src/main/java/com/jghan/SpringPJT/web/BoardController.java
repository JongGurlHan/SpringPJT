package com.jghan.SpringPJT.web;

import com.jghan.SpringPJT.config.auth.PrincipalDetails;
import com.jghan.SpringPJT.domain.board.Board;
import com.jghan.SpringPJT.service.BoardService;
import com.jghan.SpringPJT.web.dto.board.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm(){

        return "board/boardInput";
    }

    @PostMapping("/board/write")
    public String boardWrite(@Valid BoardDto boardDto, BindingResult bindingResult,
                             @AuthenticationPrincipal PrincipalDetails principalDetails,
                             Model model, MultipartFile file) throws Exception{

        Board board = boardDto.toEntity();
        boardService.write(board, principalDetails.getUser().getId());


        model.addAttribute("message", "글 작성이 완료됐습니다.");
        model.addAttribute("url", "/board/list");
        return "message/message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword){

        Page<Board> list = null;
        if(searchKeyword == null){
            list = boardService.boardList(pageable);
        }else{
            list = boardService.boardSearchList(searchKeyword, pageable);
        }


        int nowPage = list.getPageable().getPageNumber() + 1; //페이지는 0부터 시작하므로
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        System.out.println(list.getContent());
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String boardUpdate(@PathVariable int id,
                              @Valid BoardDto boardDto,
                              BindingResult bindingResult) {

//        Board boardTemp = boardService.boardView(id);
//        boardTemp.setTitle(board.getTitle());
//        boardTemp.setContent(board.getContent());

//        System.out.println("boardDto:"+ boardDto);

        Board newBoard = boardService.update(id, boardDto.toEntity());

        System.out.println("newBoard:" + newBoard);

        return "redirect:/board/list";
    }


}
