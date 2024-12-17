package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.*;
import com.piotrkowalczykk.dormitory_management_app.security.exception.EmailSendingException;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.model.Role;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.security.repository.RoleRepository;
import com.piotrkowalczykk.dormitory_management_app.security.utils.EmailService;
import com.piotrkowalczykk.dormitory_management_app.security.utils.JsonWebToken;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuthUserRepository authUserRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JsonWebToken jsonWebToken;
    private static final int DURATION_IN_MINUTES = 1;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(PasswordEncoder passwordEncoder, EmailService emailService, AuthUserRepository authUserRepository, RoleRepository roleRepository, AuthenticationManager authenticationManager, JsonWebToken jsonWebToken) {
        this.passwordEncoder = passwordEncoder;
        this.authUserRepository = authUserRepository;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jsonWebToken = jsonWebToken;
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
        String hashedEmailCode = passwordEncoder.encode(emailCode);

        AuthUser authUser = new AuthUser();
        authUser.setEmail(registerRequest.getEmail());
        authUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        authUser.setFirstName(registerRequest.getFirstName());
        authUser.setLastName(registerRequest.getLastName());
        authUser.setGender(registerRequest.getGender());
        authUser.setDateOfBirth(registerRequest.getDateOfBirth());
        authUser.setCreatedAt(LocalDate.now());
        authUser.setEmailVerificationCode(hashedEmailCode);
        authUser.setEmailVerificationCodeExpiryDate(LocalDateTime.now().plusMinutes(DURATION_IN_MINUTES));
        Role roles = roleRepository.findByName("USER").get();
        authUser.setRoles(Collections.singletonList(roles));
        authUserRepository.save(authUser);

        String subject = "Email Verification";
        String content = String.format("Enter this code to verify your email: %s. The code will expire in %s minutes.", emailCode, DURATION_IN_MINUTES);

        try {
            emailService.sendEmail(authUser.getEmail(), subject, content);
        } catch (MessagingException e) {
            throw new EmailSendingException("Error sending verification email");
        }

        return new RegisterResponse("User registered successfully");
    }

    @Override
    public ValidateEmailResponse validateEmailVerificationCode(ValidateEmailRequest validateEmailRequest){
        Optional<AuthUser> user = authUserRepository.findByEmail(validateEmailRequest.getEmail());
        if(user.isPresent() && passwordEncoder.matches(validateEmailRequest.getEmailCode(), user.get().getEmailVerificationCode()) && !user.get().getEmailVerificationCodeExpiryDate().isBefore(LocalDateTime.now())){
            user.get().setEmailVerificationCode(null);
            user.get().setEmailVerificationCodeExpiryDate(null);
            user.get().setEmailVerified(true);
            authUserRepository.save(user.get());
            return new ValidateEmailResponse("Email verified successfully");
        } else if (user.isPresent() && passwordEncoder.matches(validateEmailRequest.getEmailCode(), user.get().getEmailVerificationCode()) && user.get().getEmailVerificationCodeExpiryDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Email verification token expired.");
        } else {
            throw new IllegalArgumentException("Email verification token failed.");
        }
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jsonWebToken.generateToken(authentication);
        return new LoginResponse(token, "Bearer");
    }

    @Override
    public void sendEmailVerificationCode(SendEmailRequest sendEmailRequest){
        Optional<AuthUser> user = authUserRepository.findByEmail(sendEmailRequest.getEmail());
        if(user.isPresent() && !user.get().isEmailVerified()){
            String emailCode = generateEmailVerificationCode();
            String hashedEmailCode = passwordEncoder.encode(emailCode);
            user.get().setEmailVerificationCode(hashedEmailCode);
            user.get().setEmailVerificationCodeExpiryDate(LocalDateTime.now().plusMinutes(DURATION_IN_MINUTES));
            authUserRepository.save(user.get());

            String subject = "Email verification";
            String content = String.format("Enter this code to verify your email: %s. The code will expire in %s minutes.", emailCode, DURATION_IN_MINUTES);

            try {
                emailService.sendEmail(sendEmailRequest.getEmail(), subject, content);
            } catch (MessagingException e) {
                throw new EmailSendingException("Error sending verification email");
            }

        } else {
            throw new IllegalArgumentException("Email verification token failed, or email is already verified");
        }
    }
}
