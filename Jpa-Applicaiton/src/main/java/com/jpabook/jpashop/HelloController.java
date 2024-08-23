package com.jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) { //model에 데이터를 심어서 view로 넘길 수 있음
        model.addAttribute("data", "hello!!!");
        return "hello";   //return -> 화면 이름
    }
}
