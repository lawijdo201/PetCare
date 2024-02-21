package com.example.petcare.service.PetCareService.Impl;

import com.example.petcare.data.dao.PetCareDAO;
import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.data.dto.PetCare.GiveCareDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.service.PetCareService.PetCareService;
import com.example.petcare.service.Redis.RedisService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCareServiceImpl implements PetCareService {
    private final PetCareDAO petCareDAO;
    private final UserDAO userDAO;
    private final RedisService redisService;

    public PetCareServiceImpl(PetCareDAO petCareDAO, UserDAO userDAO, RedisService redisService) {
        this.petCareDAO = petCareDAO;
        this.userDAO = userDAO;
        this.redisService = redisService;
    }

    @Override
    public void saveRole(PetCare petCare) {
        petCareDAO.saveMember(petCare);
    }

    @Override
    public void saveBoard() {
        UserEntity userEntity = userDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        redisService.addValue(userEntity.getUsername(), userEntity.getUserCareService());
    }

    //게시글 모두 불러오기
    @Override
    public List<UserCareService> getBoardList(){
        return petCareDAO.getBoardList();
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
    public boolean existByusername(String username){
        return petCareDAO.existByusername(username);
    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public void saveInfo(UserCareService userCareService) {
        petCareDAO.savePetCare(userCareService);
    }

    @Override
    public boolean existByusernameFromUserCareService(String username){

        return petCareDAO.existByUsernameFromUserCareService(username);
    }

}
