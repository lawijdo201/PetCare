package com.example.petcare.controller;

import com.example.petcare.data.dto.Board.BoardDTO;
import com.example.petcare.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/community")
public class BoardWriteController {
    private final BoardService boardService;

    public BoardWriteController(BoardService boardService) {
        this.boardService = boardService;
    }


    //글 목록 불러오기
    @RequestMapping("/list")
    public String showBoard(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "community";
    }

    //글 작성
    @RequestMapping("/write")
    public String writeBoard(Model model) {
        return "write";
    }

    @PostMapping("/writedo")
    public String writeDo(BoardDTO boardDTO, Model model) {
        boardService.saveBoard(boardDTO);
        return "list";
    }

    //글 보기
    @GetMapping("/view")
    public String viewBoard(Model model, Integer id) {
        model.addAttribute("board", boardService.getBoard(id));
        model.addAttribute("nearById", boardService.getNearByBoard(id));
        return "boardView";
    }

    //글 삭제
    @RequestMapping("/delete")
    public String deleteBoard(Integer id, Model model) {
        boardService.deleteBoard(id);
        return "redirect:/community/list";
    }

    //글 수정
    @GetMapping("/modify")
    public String modifyBoard(@RequestParam("id") Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("board", boardService.getBoard(id));
        return "boardModify";
    }
    @PostMapping("/modifydo")
    public String modifyDo(BoardDTO NewBoard, Model model){
        boardService.updateBoard(NewBoard);
        return "redirect:/community/list";
    }
}
