package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterRequest;
import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterResponse;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthRepository;
import com.piotrkowalczykk.dormitory_management_app.security.utils.Encoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService{

    private final Encoder encoder;
    private final AuthRepository authRepository;
    private final int durationInMinutes = 1;

    public AuthServiceImpl(Encoder encoder, AuthRepository authRepository) {
        this.encoder = encoder;
        this.authRepository = authRepository;
    }

    @Override
    public String generateEmailVerificationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder emailCode = new StringBuilder();
        for(int i = 6; i >= 0; i--){
            emailCode.append(random.nextInt(10));
        }
        return emailCode.toString();
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest registerRequest){

        String emailCode = generateEmailVerificationCode();
        String hashedEmailCode = encoder.encode(emailCode);

        AuthUser authUser = new AuthUser();
        authUser.setEmail(registerRequest.getEmail());
        authUser.setPassword(registerRequest.getPassword()); //ENCRYPT
        authUser.setFirstName(registerRequest.getFirstName());
        authUser.setLastName(registerRequest.getLastName());
        authUser.setGender(registerRequest.getGender());
        authUser.setDateOfBirth(registerRequest.getDateOfBirth());
        authUser.setCreatedAt(LocalDate.now());
        authUser.setEmailVerificationCode(hashedEmailCode);
        authUser.setEmailVerificationCodeExpiryDate(LocalDateTime.now().plusMinutes(durationInMinutes));
        authRepository.save(authUser);
        return new RegisterResponse("User registered successfully", "token");
    }
}
