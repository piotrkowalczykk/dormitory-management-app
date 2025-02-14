package com.piotrkowalczykk.dormitory_management_app.feed.repository;

import com.piotrkowalczykk.dormitory_management_app.feed.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
