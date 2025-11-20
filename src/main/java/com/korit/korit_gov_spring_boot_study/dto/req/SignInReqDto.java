package com.korit.korit_gov_spring_boot_study.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInReqDto {
    private String username;
    private String password;
}
