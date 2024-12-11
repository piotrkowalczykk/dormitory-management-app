package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterRequest;
import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterResponse;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest){
        AuthUser authUser = new AuthUser();
        authUser.setEmail(registerRequest.getEmail());
        authUser.setPassword(registerRequest.getPassword()); //ENCRYPT
        authUser.setFirstName(registerRequest.getFirstName());
        authUser.setLastName(registerRequest.getLastName());
        authUser.setGender(registerRequest.getGender());
        authUser.setDateOfBirth(registerRequest.getDateOfBirth());
        authUser.setCreatedAt(LocalDate.now());
        authRepository.save(authUser);
        return new RegisterResponse("User registered successfully", "token");
    }
}
