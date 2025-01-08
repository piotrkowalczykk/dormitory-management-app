package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import com.piotrkowalczykk.dormitory_management_app.security.utils.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService{
    private final JsonWebToken jsonWebToken;
    private final AcademyRepository academyRepository;
    private final AuthUserRepository authUserRepository;
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    public FeedServiceImpl(AcademyRepository academyRepository, AuthUserRepository authUserRepository, JsonWebToken jsonWebToken) {
        this.academyRepository = academyRepository;
        this.authUserRepository = authUserRepository;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public List<Academy> getAllAcademies() {
        return academyRepository.findAll();
    }

    @Override
    public UserDetailsResponse getUserDetails(String authHeader) {
        String token = authHeader.substring(7);
        String email = jsonWebToken.getEmailFromToken(token);

        AuthUser user = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isAdmin = user.getRoles().stream().anyMatch(role -> "ADMIN".equalsIgnoreCase(role.getName()));

        if(isAdmin){
            return new UserDetailsResponse(
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getGender(),
                    user.getDateOfBirth(),
                    null,
                    user.getRoles()
            );
        }

        return new UserDetailsResponse(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getDateOfBirth(),
                user.getAcademy().getName(),
                user.getRoles()
        );
    }
}
