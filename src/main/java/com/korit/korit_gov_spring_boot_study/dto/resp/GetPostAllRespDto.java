package com.korit.korit_gov_spring_boot_study.dto.resp;

import com.korit.korit_gov_spring_boot_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetPostAllRespDto {
    private String status;
    private String message;
    private List<Post> data;
}
