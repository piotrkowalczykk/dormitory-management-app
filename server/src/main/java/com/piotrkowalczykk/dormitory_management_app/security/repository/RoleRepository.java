package com.piotrkowalczykk.dormitory_management_app.security.repository;

import com.piotrkowalczykk.dormitory_management_app.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
