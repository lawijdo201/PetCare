package com.example.petcare.data.dto.AlertMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlertDTO {
    private String message;
    private String url;
}
