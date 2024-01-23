package com.example.petcare.controller;

import com.example.petcare.data.dto.Board.BoardDTO;
import com.example.petcare.service.Board.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class BoardWriteController {
    private final BoardService boardService;

    public BoardWriteController(BoardService boardService) {
        this.boardService = boardService;
    }


    //글 목록 불러오기
    @GetMapping("/list")
    public String showBoard(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page list = boardService.getBoardList(pageable);
        int CurrentPage = pageable.getPageNumber();//23

        List<Integer> num = new ArrayList<>();
        Map<String, Object> page = new HashMap<>();
        page.put("num", num);
        for (int i = CurrentPage-CurrentPage%10; i < Math.min(CurrentPage-CurrentPage%10+10,list.getTotalPages()); i++) {
            num.add(i);
        }
        page.put("CurrentPage", CurrentPage);
        System.out.println(page);


        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        return "community";
    }

    //글 작성
    @GetMapping("/write")
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
    @GetMapping("/delete")
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
