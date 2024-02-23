package com.example.petcare.service.PetCareService.Impl;

import com.example.petcare.data.dao.PetCareDAO;
import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.data.dto.PetCare.GiveCareDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.service.PetCareService.PetCareService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PetCareServiceImpl implements PetCareService {
    private final PetCareDAO petCareDAO;
    private final UserDAO userDAO;

    public PetCareServiceImpl(PetCareDAO petCareDAO, UserDAO userDAO) {
        this.petCareDAO = petCareDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void saveRole(PetCare petCare) {
        petCareDAO.saveMember(petCare);
    }

    @Override
    public void saveBoard() {
        UserEntity userEntity = userDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        userEntity.getUserCareService();
    }

    //게시글 모두 불러오기
    @Override
    public Page<UserCareService> getBoardList(Pageable pageable){
        return petCareDAO.getBoardList(pageable);
    }

    //게시글 하나 가져오기
    @Override
    @Transactional
    public GiveCareDTO getBoard(Integer id) {
        System.out.println(petCareDAO.getBoard(id).getUserEntity().getUsername());
        UserCareService userCareService = petCareDAO.getBoard(id);
        GiveCareDTO giveCareDTO = GiveCareDTO.builder()
                .username(userCareService.getUserEntity().getUsername())
                .day(userCareService.getDay())
                .walk(userCareService.getWalk())
                .idcard(userCareService.getIdcard())
                .homecam(userCareService.getHomecam())
                .meeting(userCareService.getMeeting())
                .care(userCareService.getCare())
                .content(userCareService.getContent())
                .price_perHour(userCareService.getPrice_perHour())
                .price_walk1(userCareService.getPrice_walk1())
                .price_walk2(userCareService.getPrice_walk2())
                .price_walk3(userCareService.getPrice_walk3())
                .price_walk4(userCareService.getPrice_walk4())
                .phone_number(userCareService.getPhone_number())
                .postcode(userCareService.getPostcode())
                .address(userCareService.getAddress())
                .detailAddress(userCareService.getDetailAddress())
                .createAt(userCareService.getCreateAt())
                .id(userCareService.getId()).build();
        return giveCareDTO;
    }

    @Override
    public String findRole(String username) {
        return petCareDAO.findRole(username);
    }

    @Override
    @Transactional
    public void updateUserCareServiceTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserCareService userCareService = userDAO.findByUsername(username).getUserCareService();
        userCareService.setCreateAt(formattedDateTime);
    }

    @Override
    public boolean existByusername(String username){
        return petCareDAO.existByusername(username);
    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    @Transactional
    public void saveInfo(UserCareService userCareService) {
        petCareDAO.savePetCare(userCareService);
    }

    @Override
    public boolean existByusernameFromUserCareService(String username){

        return petCareDAO.existByUsernameFromUserCareService(username);
    }

    @Override
    @Transactional
    public void removeUserCareService(String username) {
        UserEntity userEntity = userDAO.findByUsername(username);
        if (userDAO.findByUsername(username).getUserCareService() != null) {
            petCareDAO.deleteById(userEntity.getUserCareService().getId());
        }
    }

    @Override
    public NearByBoardDTO getNearByBoard(Integer id) {
        return petCareDAO.getNearByBoard(id);
    }
}
