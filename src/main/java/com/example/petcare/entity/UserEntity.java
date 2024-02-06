package com.example.petcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity//Entity 설정
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String pw;

    @Column(unique = true)
    private String email;

    private String role;

    @OneToMany(mappedBy="userEntity")
    private List<Board> boards;

    @OneToOne
    private PetCare petCare;

    @OneToMany(mappedBy = "userEntity")
    private List<PetInfo> petInfoLists;

    @OneToOne
    private UserCareService userCareService;

    public void addBoard(Board board) {
        board.setUserEntity(this);  //주인 설정
        getBoards().add(board);
    }

    public void addPetInfo(PetInfo petInfo) {
        petInfo.setUserEntity(this);  //주인 설정
        getPetInfoLists().add(petInfo);
    }

}
