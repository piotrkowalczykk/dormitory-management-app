package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.admin.repository.AcademyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService{
    private final AcademyRepository academyRepository;

    public FeedServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    @Override
    public List<Academy> getAllAcademies() {
        return academyRepository.findAll();
    }
}
