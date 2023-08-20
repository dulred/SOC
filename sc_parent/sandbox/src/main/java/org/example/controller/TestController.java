package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "测试")
public class TestController {

@GetMapping("/test")
@Operation(summary = "cc")
    public String test(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "index"; // 返回模板名称
    }


    @ResponseBody
    @Operation(summary = "测试接口")
    @GetMapping("/look")
    public String test2() {
        return "nishizhu";
    }

}
