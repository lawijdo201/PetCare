package com.example.petcare.data.dto.PetCare;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class GiveCareDTO {
    @NotNull(message = "최소 하루는 선택해야합니다.")
    private List<String> day;
    private String walk;
    private String idcard;
    private String homecam;
    private String meeting;
    private String care;
    @NotBlank(message = "소개글은 비어있으면 안됩니다.")
    @Size(max = 1000, message = "1000글자 이내로 작성해야 합니다.")
    private String content;
    @Positive(message = "필수로 작성해야 합니다.")
    private int price_perHour;
    @Positive(message = "필수로 작성해야 합니다.")
    private int price_walk1;
    @Positive(message = "필수로 작성해야 합니다.")
    private int price_walk2;
    @Positive(message = "필수로 작성해야 합니다.")
    private int price_walk3;
    @Positive(message = "필수로 작성해야 합니다.")
    private int price_walk4;
    @NotBlank
    private String postcode;
    @NotBlank(message = "필수로 작성해야 합니다.")
    private String address;
    @NotBlank(message = "필수로 작성해야 합니다.")
    private String detailAddress;
}
