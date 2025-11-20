package com.korit.korit_gov_spring_boot_study.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRespDto {
    private String status;
    private String message;
}
