package com.piotrkowalczykk.dormitory_management_app.customer.repository;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllByAuthorId(Long id);
}
