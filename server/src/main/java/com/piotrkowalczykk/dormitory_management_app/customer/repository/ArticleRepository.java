package com.piotrkowalczykk.dormitory_management_app.customer.repository;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<List<Article>> findAllByAuthorEmail(String email);
}
