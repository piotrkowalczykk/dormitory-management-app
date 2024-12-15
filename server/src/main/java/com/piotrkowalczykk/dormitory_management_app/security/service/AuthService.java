package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.*;

public interface AuthService {
    public String generateEmailVerificationCode();
    public ValidateEmailResponse validateEmailVerificationCode(ValidateEmailRequest validateEmailRequest);
    public RegisterResponse registerUser(RegisterRequest registerRequest);
}
