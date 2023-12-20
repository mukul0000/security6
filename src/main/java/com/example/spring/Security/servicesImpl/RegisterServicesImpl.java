package com.example.spring.Security.servicesImpl;

import com.example.spring.Security.dto.userRegisterDto.ResponseDto;
import com.example.spring.Security.dto.userRegisterDto.UserRegisterDto;
import com.example.spring.Security.entiry.Roles;
import com.example.spring.Security.entiry.User;
import com.example.spring.Security.repository.UserRepository;
import com.example.spring.Security.security.config.JwtService;
import com.example.spring.Security.services.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServicesImpl implements RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseDto register(UserRegisterDto userRegisterDto) {

        User user = new User();

        user.setEmail(userRegisterDto.getEmail());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setEmail(userRegisterDto.getEmail());
        user.setRole(Roles.USER);

        userRepository.save(user);

        var token  = jwtService.generateToken(user);

        return ResponseDto.builder().data(token).message("scuess").status(true).build();
    }
}
