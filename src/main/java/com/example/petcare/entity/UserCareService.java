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
    @Column
    private String username;

    @ElementCollection
    @Column(name = "day")
    private List<String> day;

    @Column
    private String walk;
    @Column
    private String idcard;
    @Column
    private String homecam;
    @Column
    private String meeting;
    @Column
    private String care;
    @Column
    private String content;
    @Column
    private int price_perHour;
    @Column
    private int price_walk1;
    @Column
    private int price_walk2;
    @Column
    private int price_walk3;
    @Column
    private int price_walk4;
    @Column
    private String postcode;
    @Column
    private String address;
    @Column
    private String detailAddress;
}
