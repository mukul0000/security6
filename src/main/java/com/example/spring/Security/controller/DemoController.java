package com.example.spring.Security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoController {


    @GetMapping("/test")
    public String demo(){
        return "yes you are doing good";
    }

    @GetMapping("/401")
    public String demo1(){
        return "yes you are doing good";
    }


}
