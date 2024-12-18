package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.*;

public interface AuthService {
    public String generateEmailVerificationCode();
    public ValidateEmailResponse validateEmailVerificationCode(ValidateEmailRequest validateEmailRequest);
    public RegisterResponse registerUser(RegisterRequest registerRequest);
    public LoginResponse loginUser(LoginRequest loginRequest);
    public void sendEmailVerificationCode(SendEmailCodeRequest sendEmailCodeRequest);
    public void sendResetPasswordCode(SendEmailCodeRequest sendEmailCodeRequest);
    public ValidatePasswordResetResponse resetPassword(ValidatePasswordResetRequest validatePasswordResetRequest);
}
