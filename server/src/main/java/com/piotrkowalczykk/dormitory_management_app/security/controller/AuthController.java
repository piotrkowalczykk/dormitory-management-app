package com.piotrkowalczykk.dormitory_management_app.security.controller;

import com.piotrkowalczykk.dormitory_management_app.security.dto.*;
import com.piotrkowalczykk.dormitory_management_app.security.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/validate-email")
    public ValidateEmailResponse validateEmail(@RequestBody ValidateEmailRequest validateEmailRequest){
        return authService.validateEmailVerificationCode(validateEmailRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/resend-email-verification-code")
    public String resendEmailVerificationCode(@RequestBody SendEmailCodeRequest sendEmailCodeRequest){
        authService.sendEmailVerificationCode(sendEmailCodeRequest);
        return "Email verification code sent successfully";
    }

    @PostMapping("/send-password-reset-code")
    public ResponseEntity<String> sendPasswordResetCode(@RequestBody @Valid SendEmailCodeRequest sendEmailCodeRequest){
        authService.sendResetPasswordCode(sendEmailCodeRequest);
        return ResponseEntity.ok("Reset password code sent successfully");
    }

    @PostMapping("/reset-password")
    public ValidatePasswordResetResponse resetPassword(@RequestBody @Valid ValidatePasswordResetRequest validatePasswordResetRequest){
        return authService.resetPassword(validatePasswordResetRequest);
    }
}
