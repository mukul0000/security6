package com.example.spring.Security.dto.userRegisterDto;


import lombok.*;

import java.security.SecureRandom;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
