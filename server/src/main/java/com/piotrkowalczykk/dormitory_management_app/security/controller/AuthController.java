package com.piotrkowalczykk.dormitory_management_app.security.controller;

import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterRequest;
import com.piotrkowalczykk.dormitory_management_app.security.dto.RegisterResponse;
import com.piotrkowalczykk.dormitory_management_app.security.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){
        return authService.registerUser(registerRequest);
    }

}
