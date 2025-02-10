package com.piotrkowalczykk.dormitory_management_app.customer.service;

import com.piotrkowalczykk.dormitory_management_app.customer.dto.ArticleRequest;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;

import java.util.List;

public interface CustomerService {
    public List<Student> getAllStudents();
    public Article createArticle(ArticleRequest articleRequest);
    public void deleteArticle(Long articleId);
    public Article editArticle(Long articleId, ArticleRequest articleRequest);
}
