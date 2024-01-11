package com.example.petcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String filename;
    @Column
    private String filepath;
}
