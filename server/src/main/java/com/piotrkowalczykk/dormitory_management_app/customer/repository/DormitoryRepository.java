package com.piotrkowalczykk.dormitory_management_app.customer.repository;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, Long> {
    List<Dormitory> findAllByAcademyEmail(String email);
}
