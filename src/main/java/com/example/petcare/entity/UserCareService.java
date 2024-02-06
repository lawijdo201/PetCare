package com.example.petcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCareService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    @Column(name = "day")
    private List<String> day;
    private String walk;
    private String idcard;
    private String homecam;
    private String meeting;
    private String care;
    private String content;
    private int price_perHour;
    private int price_walk1;
    private int price_walk2;
    private int price_walk3;
    private int price_walk4;
    private String postcode;
    private String address;
    private String detailAddress;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
