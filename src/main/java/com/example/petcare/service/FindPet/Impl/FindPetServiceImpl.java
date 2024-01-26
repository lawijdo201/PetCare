package com.example.petcare.service.FindPet.Impl;

import com.example.petcare.data.dao.FindPetDAO;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.data.dto.PetInfo.PetDTO;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.service.FindPet.FindPetService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FindPetServiceImpl implements FindPetService {
    private final FindPetDAO findPetDAO;

    public FindPetServiceImpl(FindPetDAO findPetDAO) {
        this.findPetDAO = findPetDAO;
    }

    //글 저장
    @Transactional
    public void saveBoard(PetDTO petDTO, MultipartFile file) {
        PetInfo petInfo = PetInfo.builder()
                .title(petDTO.getTitle())
                .content(petDTO.getContent())
                .user(SecurityContextHolder.getContext().getAuthentication().getName())
                .build();

        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(filePath, fileName);

        petInfo.setFilename(fileName);
        petInfo.setFilepath("/files/"+fileName);
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            System.out.println(e);
        }

        findPetDAO.write(petInfo);
    }

    //글 목록 불러오기
    public Page<PetInfo> getBoardList(Pageable pageable) {
        return findPetDAO.getBoardList(pageable);
    }

    //글 내용 불러오기
    public PetInfo getBoard(Integer id){
        return findPetDAO.getBoard(id);
    }

    //이전글과 다음글 PK 불러오기
    public NearByBoardDTO getNearByBoard(Integer id){return findPetDAO.getNearByBoard(id);}

    //글 삭제
    public void deleteBoard(Integer id) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("static" + findPetDAO.getBoard(id).getFilepath());

        //저장된 파일이 있으면 삭제
        if (resource != null) {
            try {
                Path filePath = Paths.get(resource.toURI());
                Files.delete(filePath);
                System.out.println("파일 삭제 완료");
            } catch (IOException | URISyntaxException e) {
                System.out.println("파일 삭제 오류 발생: " + e.getMessage());
            }
        }

        findPetDAO.deleteBoard(id);
        log.info("{}번째 게시물이 삭제되었습니다.", id);
    }

    //글 업데이트
    @Transactional
    public void updateBoard(PetInfo NewPetInfo, MultipartFile file){
        //Entity를 영속상태로 가져옴
        PetInfo OldPetInfo = findPetDAO.getBoard(NewPetInfo.getId());
        System.out.println("기존의 Board");
        System.out.println(OldPetInfo.getId());
        System.out.println(OldPetInfo.getTitle());
        System.out.println(OldPetInfo.getContent());

        System.out.println("새로운 Board");
        System.out.println(NewPetInfo.getId());
        System.out.println(NewPetInfo.getTitle());
        System.out.println(NewPetInfo.getContent());

        //Dirty Checking
        OldPetInfo.setTitle(NewPetInfo.getTitle());
        OldPetInfo.setContent(NewPetInfo.getContent());
    }
}
