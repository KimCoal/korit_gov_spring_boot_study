package com.korit.korit_gov_spring_boot_study.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
class TodoDto {
    private int todoId;
    private String title;
    private String content;
}

@Controller
public class TodoController {
    private List<TodoDto> todos = new ArrayList<>();
    // todo 넣고 추가되었습니다
    // 목록으로 가기 하면 목록 나오게
    @GetMapping("/todo")
    public String todo() {
        return "todo";
    }

    @PostMapping("/todo")
    public String todoSubmit(@RequestParam String title, @RequestParam String content, Model model) {
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
