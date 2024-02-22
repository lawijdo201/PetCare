package com.example.petcare.entity;

import com.example.petcare.data.dto.User.Join_Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    private Join_Provider joinProvider;

    @Column(unique = true)
    private String email;

    private String role;

    @OneToOne(mappedBy = "userEntity")
    private PetCare petCare;

    @OneToOne(mappedBy = "userEntity")
    private UserCareService userCareService;
}
