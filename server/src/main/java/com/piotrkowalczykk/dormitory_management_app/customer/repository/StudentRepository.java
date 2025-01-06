package com.piotrkowalczykk.dormitory_management_app.customer.repository;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<List<Student>> getAllStudentsByAcademyId(Long academyId);
    boolean existsByEmail(String email);
}
