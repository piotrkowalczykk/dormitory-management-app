package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.SelectAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import com.piotrkowalczykk.dormitory_management_app.security.repository.AuthUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService{
    private final AcademyRepository academyRepository;
    private final AuthUserRepository authUserRepository;
    private static final Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    public FeedServiceImpl(AcademyRepository academyRepository, AuthUserRepository authUserRepository) {
        this.academyRepository = academyRepository;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public List<Academy> getAllAcademies() {
        return academyRepository.findAll();
    }

    @Override
    public void selectAcademy(SelectAcademyRequest selectAcademyRequest) {
        AuthUser user = authUserRepository.findByEmail(selectAcademyRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Academy academy = academyRepository.findById(selectAcademyRequest.getAcademyId())
                .orElseThrow(() -> new IllegalArgumentException("Academy not found"));

        user.setAcademy(academy);
        authUserRepository.save(user);
    }
}
