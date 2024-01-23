package com.example.petcare.data.dto.PetInfo;

import com.example.petcare.data.dto.Search.SearchDTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetDTO  {
    @NotBlank(message = "제목은 빈칸이면 안됩니다.")
    @Size(min = 1, max = 20, message = "제목의 크기는 1부터 20 사이어야만 합니다.")
    private String title;
    @NotBlank(message = "내용은 빈칸이면 안됩니다.")
    @Size(min = 1, max = 1000, message = "내용의 크기는 1부터 1000 사이어야만 합니다.")
    private String content;
    private String filename;
    private String filepath;
}
