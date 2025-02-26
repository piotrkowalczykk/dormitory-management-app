package com.piotrkowalczykk.dormitory_management_app.admin.service;

import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.admin.dto.AddAcademyResponse;
import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;

import java.util.Optional;

public interface AcademyService {
    public AddAcademyResponse addAcademy(AddAcademyRequest addAcademyRequest);

    Optional<Academy> findByName(String name);
}
