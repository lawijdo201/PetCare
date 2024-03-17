package com.example.petcare;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PetCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetCareApplication.class, args);
    }

}
