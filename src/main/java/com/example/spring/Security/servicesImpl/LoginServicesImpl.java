package com.example.spring.Security.servicesImpl;

import com.example.spring.Security.dto.userRegisterDto.LoginDto;
import com.example.spring.Security.dto.userRegisterDto.ResponseDto;
import com.example.spring.Security.repository.UserRepository;
import com.example.spring.Security.security.config.JwtService;
import com.example.spring.Security.services.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginServicesImpl implements LoginService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtService jwtService;
    public LoginServicesImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseDto login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(),
                        loginDto.getPassword()
                )
        );

        var user  = userRepository.findByEmail(loginDto.getUserName()).orElseThrow();
         var jwtToken = jwtService.generateToken(user);

        return ResponseDto.builder().data(jwtToken).build();
    }
}
