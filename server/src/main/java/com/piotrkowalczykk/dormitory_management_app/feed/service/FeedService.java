package com.piotrkowalczykk.dormitory_management_app.feed.service;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleResponse;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.feed.dto.UserDetailsResponse;

import java.util.List;

public interface FeedService {
    public List<Academy> getAllAcademies();
    public UserDetailsResponse getUserDetails(String authHeader);
    public List<ArticleResponse> getAllArticles();
}
