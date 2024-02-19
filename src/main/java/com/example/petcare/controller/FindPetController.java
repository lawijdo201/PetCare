package com.example.petcare.controller;

import com.example.petcare.data.dto.AlertMessage.AlertDTO;
import com.example.petcare.data.dto.PetInfo.PetDTO;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.service.FindPet.FindPetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/findPet")
public class FindPetController {
    private final FindPetService findPetService;
    public FindPetController(FindPetService findPetService) {
        this.findPetService = findPetService;
    }

    //글 목록 불러오기
    @GetMapping("/list")
    public String showBoard(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page list = findPetService.getBoardList(pageable);
        int CurrentPage = pageable.getPageNumber();

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
        return "FindPetMain";
    }

    //글 작성
    @GetMapping("/write")
    public String writeBoard(Model model) {
        return "FindPetWrite";
    }

    @PostMapping("/writedo")
    public String writeDo(PetDTO petDTO, MultipartFile file, Model model) {
        findPetService.saveBoard(petDTO, file);
        AlertDTO alertDTO = new AlertDTO("글이 작성되었습니다.","list");
        model.addAttribute("Message", alertDTO);
        return "Alert";
    }

    //글 보기
    @GetMapping("/view")
    public String viewBoard(Model model, Integer id) {
        model.addAttribute("petInfo", findPetService.getBoard(id));
        model.addAttribute("nearById", findPetService.getNearByBoard(id));
        return "FindPetView";
    }

    //글 삭제
    @GetMapping("/delete")
    public String deleteBoard(@RequestParam("id") Integer id, @RequestParam("user") String user, Model model) {
        //작성자인지 체크
        if (user.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            findPetService.deleteBoard(id);
        } else {
            //오류 발생
            AlertDTO alertDTO = new AlertDTO("사용자의 게시물이 아닙니다.","list");
            model.addAttribute("Message", alertDTO);
            return "Alert";
        }
        AlertDTO alertDTO = new AlertDTO("글이 삭제되었습니다.","/community/list");
        model.addAttribute("Message", alertDTO);
        return "Alert";
    }

    //글 수정
    @GetMapping("/modify")
    public String modifyBoard(Integer id, String user, Model model) {
        //작성자인지 체크
        if (user.equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            model.addAttribute("petInfo", findPetService.getBoard(id));
            return "FindPetModify";
        } else {
            //오류 발생
            AlertDTO alertDTO = new AlertDTO("사용자의 게시물이 아닙니다.","list");
            model.addAttribute("Message", alertDTO);
            return "Alert";
        }
    }
    @PostMapping("/modifydo")
    public String modifyDo(PetInfo NewPetInfo, MultipartFile file, Model model){
        findPetService.updateBoard(NewPetInfo, file);
        return "redirect:/findPet/list";
    }
}
