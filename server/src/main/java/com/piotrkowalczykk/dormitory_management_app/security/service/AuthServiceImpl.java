package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.*;
import com.piotrkowalczykk.dormitory_management_app.security.exception.CustomAuthenticationException;
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
    public LoginResponse loginUser(LoginRequest loginRequest){

        Optional<AuthUser> user = authUserRepository.findByEmail(loginRequest.getEmail());

        if(user.isEmpty() && !loginRequest.getEmail().isEmpty()){
            throw new CustomAuthenticationException("email: email does not exist");
        } else if (user.isEmpty() && loginRequest.getEmail().isEmpty()){
            if(loginRequest.getPassword().isEmpty()){
                throw new CustomAuthenticationException("email: email address is mandatory, password: password is mandatory");
            } else {
                throw new CustomAuthenticationException("email: email address is mandatory");
            }
        } else if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())){
            throw new CustomAuthenticationException("password: invalid password");
        } else if (!user.get().isEmailVerified()){
            throw new CustomAuthenticationException("email: email is not verified");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jsonWebToken.generateToken(authentication);
        return new LoginResponse(token, "Bearer");
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
            throw new IllegalArgumentException("email: email verification code expired");
        } else {
            throw new IllegalArgumentException("email: email verification failed");
        }
    }

    @Override
    public void sendEmailVerificationCode(SendEmailCodeRequest sendEmailCodeRequest){
        Optional<AuthUser> user = authUserRepository.findByEmail(sendEmailCodeRequest.getEmail());
        if(user.isPresent() && !user.get().isEmailVerified()){
            String emailCode = generateEmailVerificationCode();
            String hashedEmailCode = passwordEncoder.encode(emailCode);
            user.get().setEmailVerificationCode(hashedEmailCode);
            user.get().setEmailVerificationCodeExpiryDate(LocalDateTime.now().plusMinutes(DURATION_IN_MINUTES));
            authUserRepository.save(user.get());

            String subject = "Email verification";
            String content = String.format("Enter this code to verify your email: %s. The code will expire in %s minutes.", emailCode, DURATION_IN_MINUTES);

            try {
                emailService.sendEmail(sendEmailCodeRequest.getEmail(), subject, content);
            } catch (MessagingException e) {
                throw new EmailSendingException("email: error sending verification email");
            }

        } else {
            throw new IllegalArgumentException("email: email verification code failed, or email is already verified");
        }
    }

    @Override
    public void sendResetPasswordCode(SendEmailCodeRequest sendEmailCodeRequest) {
        String passwordResetCode = generateEmailVerificationCode();
        String hashedPasswordResetCode = passwordEncoder.encode(passwordResetCode);

        Optional<AuthUser> user = authUserRepository.findByEmail(sendEmailCodeRequest.getEmail());
        if(user.isPresent()) {
            user.get().setPasswordResetCode(hashedPasswordResetCode);
            user.get().setPasswordResetCodeExpiryDate(LocalDateTime.now().plusMinutes(DURATION_IN_MINUTES));
            authUserRepository.save(user.get());

            String subject = "Password Reset";
            String content = String.format("Enter this code to reset your password: %s. The code will expire in %s minutes.", passwordResetCode, DURATION_IN_MINUTES);

            try {
                emailService.sendEmail(sendEmailCodeRequest.getEmail(), subject, content);
            } catch (MessagingException e) {
                throw new EmailSendingException("Error sending password reset email");
            }
        } else {
            throw new IllegalArgumentException("email: user not found");
        }
    }

    @Override
    public ValidatePasswordResetResponse resetPassword(ValidatePasswordResetRequest validatePasswordResetRequest){
        Optional<AuthUser> user = authUserRepository.findByEmail(validatePasswordResetRequest.getEmail());
        if(user.isPresent() && passwordEncoder.matches(validatePasswordResetRequest.getEmailCode(), user.get().getPasswordResetCode()) && !user.get().getPasswordResetCodeExpiryDate().isBefore(LocalDateTime.now())){
            user.get().setPassword(passwordEncoder.encode(validatePasswordResetRequest.getNewPassword()));
            user.get().setPasswordResetCode(null);
            user.get().setPasswordResetCodeExpiryDate(null);
            authUserRepository.save(user.get());
            return new ValidatePasswordResetResponse("Password reset successfully");
        } else if (user.isPresent() && passwordEncoder.matches(validatePasswordResetRequest.getEmailCode(), user.get().getPasswordResetCode()) && user.get().getPasswordResetCodeExpiryDate().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("emailCode: password reset code expired");
        } else {
            throw new IllegalArgumentException("emailCode: password reset failed");
        }
    }
}
