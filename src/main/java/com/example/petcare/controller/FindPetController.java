package com.example.petcare.controller;

import com.example.petcare.entity.PetInfo;
import com.example.petcare.service.FindPetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/findPet")
public class FindPetController {
    private final FindPetService findPetService;
    public FindPetController(FindPetService findPetService) {
        this.findPetService = findPetService;
    }

    //글 목록 불러오기
    @RequestMapping("/list")
    public String showBoard(Model model) {
        model.addAttribute("list", findPetService.getBoardList());
        return "FindPetMain";
    }

    //글 작성
    @RequestMapping("/write")
    public String writeBoard(Model model) {
        return "FindPetWrite";
    }

    @PostMapping("/writedo")
    public String writeDo(PetInfo petInfo, MultipartFile file, Model model) {
        findPetService.saveBoard(petInfo, file);
        return "list";
    }

    //글 보기
    @GetMapping("/view")
    public String viewBoard(Model model, Integer id) {
        model.addAttribute("petInfo", findPetService.getBoard(id));
        model.addAttribute("nearById", findPetService.getNearByBoard(id));
        return "FindPetView";
    }

    //글 삭제
    @RequestMapping("/delete")
    public String deleteBoard(Integer id, Model model) {
        findPetService.deleteBoard(id);
        return "redirect:/findPet/list";
    }

    //글 수정
    @GetMapping("/modify")
    public String modifyBoard(@RequestParam("id") Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("petInfo", findPetService.getBoard(id));
        System.out.println("done");
        return "FindPetModify";
    }
    @PostMapping("/modifydo")
    public String modifyDo(PetInfo NewPetInfo, MultipartFile file, Model model){
        findPetService.updateBoard(NewPetInfo, file);
        return "redirect:/findPet/list";
    }
}
