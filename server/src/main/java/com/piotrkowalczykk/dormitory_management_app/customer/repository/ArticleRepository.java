package com.piotrkowalczykk.dormitory_management_app.customer.repository;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT articles.* FROM articles " +
            "JOIN article_dormitory_visibility av ON av.article_id = articles.id " +
            "JOIN dormitories d ON d.id = av.dormitory_id " +
            "JOIN users u ON u.id = articles.author_id " +
            "WHERE u.email = :email AND av.dormitory_id = :dormitoryId " +
            "ORDER BY articles.creation_date DESC", nativeQuery = true)
    Optional<List<Article>> findAllByAuthorEmailAndDormitoryId(@Param("email") String email, @Param("dormitoryId") Long dormitoryId);

    @Query(value = "SELECT articles.* FROM articles JOIN users ON articles.author_id = users.id WHERE users.email = :email ORDER BY articles.creation_date DESC", nativeQuery = true)
    Optional<List<Article>> findAllByAuthorEmail(@Param("email") String email);
}
