package com.korit.korit_gov_spring_boot_study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

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
    public String getProfile(Model model) {
        model.addAttribute("username", "<b>coal</b>");
        model.addAttribute("isAdult",false);
        model.addAttribute("age", 25);
        Map<String, String> userList = new HashMap<>();
        userList.put("coal", "27");
        userList.put("coal1", "27");
        userList.put("coal2", "27");
        userList.put("coal3", "27");
        model.addAttribute("userList", userList);
        return "profile.html";
    }

    @GetMapping("/search")
    public String getSearch(@RequestParam String keyword, @RequestParam String keyword2, Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("keyword2", keyword2);
        return "search.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
}
