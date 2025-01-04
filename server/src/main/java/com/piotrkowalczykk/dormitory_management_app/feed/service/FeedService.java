package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.SelectAcademyRequest;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;

import java.util.List;

public interface FeedService {
    public List<Academy> getAllAcademies();
    public void selectAcademy(SelectAcademyRequest selectAcademyRequest);
    public UserDetailsResponse getUserDetails(String authHeader);
}
