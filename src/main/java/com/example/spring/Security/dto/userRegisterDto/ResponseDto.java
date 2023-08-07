package com.example.spring.Security.dto.userRegisterDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseDto<T> {
    private T data;
    private boolean status;
    private String message;
}
