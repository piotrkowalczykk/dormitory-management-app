package com.piotrkowalczykk.dormitory_management_app.security.repository;

import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByEmail(String email);
}
