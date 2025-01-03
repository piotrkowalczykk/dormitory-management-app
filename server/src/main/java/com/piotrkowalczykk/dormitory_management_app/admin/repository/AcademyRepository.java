package com.piotrkowalczykk.dormitory_management_app.admin.repository;

import com.piotrkowalczykk.dormitory_management_app.admin.model.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {
    Optional<Academy> findByName(String name);
}
