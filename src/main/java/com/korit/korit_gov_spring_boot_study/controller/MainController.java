package com.korit.korit_gov_spring_boot_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// SSR > 즉 서버쪽에서 웹페이지를 핸더링해서 반환하는 방식
@Controller
public class MainController {

    // 동적인 요소가 없는 정적 웹페이지
    @GetMapping("/main")
    public String getMain() {
        return "main.html";
    }

    // SSR에 동적을 추가하려면 Thymleaf를 적용
    // 서버에서 HTML을 렌더링할떄, 자바 데이터를 끼워 넣을 수 있게 해주는 템플릿 엔진
    @GetMapping("/profile")
    public String getProfile() {
        return "profile.html";
    }
}
