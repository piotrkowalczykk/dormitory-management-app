package com.piotrkowalczykk.dormitory_management_app.feed.repository;

import com.piotrkowalczykk.dormitory_management_app.feed.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT posts.* FROM posts JOIN users ON posts.author_id = users.id WHERE users.academy_id = :academyId ORDER BY posts.creation_date DESC", nativeQuery = true)
    List<Post> findAllPostsByAcademyId(@Param("academyId") Long academyId);
}
