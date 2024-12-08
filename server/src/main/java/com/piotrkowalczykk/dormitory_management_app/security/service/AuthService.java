package com.piotrkowalczykk.dormitory_management_app.security.service;

import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterRequest;
import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterResponse;

public interface AuthService {
    public RegisterResponse registerUser(RegisterRequest registerRequest);
}
