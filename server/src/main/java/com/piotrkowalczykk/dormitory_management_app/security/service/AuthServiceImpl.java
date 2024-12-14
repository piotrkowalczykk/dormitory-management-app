package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterRequest;
import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterResponse;
import com.piotrkowalczykk.dormitory_management_app.security.exception.EmailSendingException;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.model.Role;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.security.repository.RoleRepository;
import com.piotrkowalczykk.dormitory_management_app.security.utils.EmailService;
import com.piotrkowalczykk.dormitory_management_app.security.utils.Encoder;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService{

    private final Encoder encoder;
    private final EmailService emailService;
    private final AuthUserRepository authUserRepository;
    private final RoleRepository roleRepository;
    private final int durationInMinutes = 1;

    public AuthServiceImpl(Encoder encoder, EmailService emailService, AuthUserRepository authUserRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.authUserRepository = authUserRepository;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
    }

    @Override
    public String generateEmailVerificationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder emailCode = new StringBuilder();
        for(int i = 6; i > 0; i--){
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
        authUser.setPassword(encoder.encode(registerRequest.getPassword()));
        authUser.setFirstName(registerRequest.getFirstName());
        authUser.setLastName(registerRequest.getLastName());
        authUser.setGender(registerRequest.getGender());
        authUser.setDateOfBirth(registerRequest.getDateOfBirth());
        authUser.setCreatedAt(LocalDate.now());
        authUser.setEmailVerificationCode(hashedEmailCode);
        authUser.setEmailVerificationCodeExpiryDate(LocalDateTime.now().plusMinutes(durationInMinutes));
        Role roles = roleRepository.findByName("USER").get();
        authUser.setRoles(Collections.singletonList(roles));
        authUserRepository.save(authUser);

        String subject = "Email verification";
        String content = String.format("Enter this code to verify your email: %s. The code will expire in %s minutes.", emailCode, durationInMinutes);
        try {
            emailService.sendEmail(authUser.getEmail(), subject, content);
        } catch (MessagingException e) {
            throw new EmailSendingException("Error sending verification email");
        }

        return new RegisterResponse("User registered successfully");
    }
}
