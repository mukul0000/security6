package com.example.spring.Security.services;

import com.example.spring.Security.dto.userRegisterDto.LoginDto;
import com.example.spring.Security.dto.userRegisterDto.ResponseDto;
import com.example.spring.Security.dto.userRegisterDto.UserRegisterDto;

public interface LoginService {

    public ResponseDto login(LoginDto loginDto);
}
