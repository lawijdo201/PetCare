package com.example.petcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCareService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn
    private UserEntity userEntity;

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
    private String createAt;
    private String phone_number;
}
