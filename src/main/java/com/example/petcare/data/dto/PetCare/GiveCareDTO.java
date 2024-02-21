package com.example.petcare.data.dto.PetCare;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
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

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식에 맞지 않습니다.")
    private String phone_number;

    @NotBlank
    private String postcode;
    @NotBlank(message = "필수로 작성해야 합니다.")
    private String address;
    @NotBlank(message = "필수로 작성해야 합니다.")
    private String detailAddress;

    private String createAt;
    private Integer id;
    private String username;
}
