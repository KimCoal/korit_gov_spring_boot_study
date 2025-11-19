package com.korit.korit_gov_spring_boot_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
class UserDto {
    private int userId;
    private String username;
    private int age;
}

@Data
@AllArgsConstructor
class TodoDto {
    private int todoId;
    private String title;
    private String content;

    static class MainController {

    }
}

// SSR > 즉 서버쪽에서 웹페이지를 핸더링해서 반환하는 방식
@Controller
public class MainController {
    private List<UserDto> users = new ArrayList<>();
    private List<TodoDto> todos = new ArrayList<>();

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

    @PostMapping("/signup")
    public String signupSubmit(@RequestParam String name, @RequestParam int age, Model model) {
        UserDto userDto = new UserDto(users.size() + 1, name, age);
        users.add(userDto);
        model.addAttribute("message", name + "님, 가입을 환영합니다.");
        return "result-page";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", users);
        return "users";
    }
    // todo 넣고 추가되었습니다
    // 목록으로 가기 하면 목록 나오게
    @GetMapping("/todo")
    public String todo() {
        return "todo";
    }

    @PostMapping("/todo")
    public String todoSubmit(@RequestParam String title,@RequestParam String content, Model model) {
        TodoDto todoDto = new TodoDto(todos.size()+1, title, content);
        model.addAttribute("message", title+" [todo] 등록완료");
        todos.add(todoDto);
        return "todo-result-page";
    }

    @GetMapping("/todos")
    public String todos(Model model) {
        model.addAttribute("todos", todos);
        return "todos";
    }
}
