package com.example.spring.Security.services;

import com.example.spring.Security.dto.userRegisterDto.ResponseDto;
import com.example.spring.Security.dto.userRegisterDto.UserRegisterDto;
import org.springframework.web.bind.annotation.ResponseBody;

public interface RegisterService {

    public ResponseDto register(UserRegisterDto userRegisterDto);
}
