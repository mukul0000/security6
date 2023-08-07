package com.example.spring.Security.controller;

import com.example.spring.Security.dto.userRegisterDto.LoginDto;
import com.example.spring.Security.dto.userRegisterDto.ResponseDto;
import com.example.spring.Security.dto.userRegisterDto.UserRegisterDto;
import com.example.spring.Security.services.LoginService;
import com.example.spring.Security.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final RegisterService registerService;
    private final LoginService loginService;


    @PostMapping("/register")
    public ResponseDto registerUser(@RequestBody UserRegisterDto userRegisterDto){

        return registerService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public ResponseDto LoginUser(@RequestBody LoginDto loginDto){

        return loginService.login(loginDto);
    }






}
