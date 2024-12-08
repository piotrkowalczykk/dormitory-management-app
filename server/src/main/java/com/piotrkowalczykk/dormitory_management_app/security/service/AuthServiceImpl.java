package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterRequest;
import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterResponse;
import com.piotrkowalczykk.dormitory_management_app.security.model.User;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword()); //ENCRYPT
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setCreatedAt(LocalDateTime.now());
        authRepository.save(user);
        return new RegisterResponse("User registered successfully", "token");
    }
}
