package com.example.petcare.controller;

import com.example.petcare.entity.Board;
import com.example.petcare.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public BoardWriteController(BoardService boardService) {
        this.boardService = boardService;
    }


    @RequestMapping("/list")
    public String showBoard(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "community";
    }

    @RequestMapping("/write")
    public String writeBoard() {
        return "write";
    }

    @GetMapping("/writedo")
    public String writeDo(Board board) {
        System.out.println("제목" + board.getTitle());
        System.out.println("본문" + board.getContent());

        boardService.saveBoard(board);
        return "list";
    }

    @GetMapping("/view")
    public String viewBoard(Model model, Integer id) {
        model.addAttribute("board", boardService.getBoard(id));
        model.addAttribute("nearById", boardService.getNearByBoard(id));
        return "boardView";
    }

    @RequestMapping("/delete")
    public String deleteBoard(Integer id) {
        boardService.deleteBoard(id);
        return "redirect:/community/list";
    }

    @GetMapping("/modify")
    public String modifyBoard(@RequestParam("id") Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("board", boardService.getBoard(id));
        return "boardModify";
    }
    @PostMapping("/modifydo")
    public String modifyDo(Board NewBoard){
        boardService.updateBoard(NewBoard);
        return "redirect:/community/list";
    }
}
