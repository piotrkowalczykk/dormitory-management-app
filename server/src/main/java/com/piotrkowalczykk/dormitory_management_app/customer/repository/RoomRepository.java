package com.piotrkowalczykk.dormitory_management_app.customer.repository;

import com.piotrkowalczykk.dormitory_management_app.customer.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "SELECT r.* FROM rooms r " +
            "JOIN dormitories d ON r.dormitory_id = d.id " +
            "JOIN academies a ON d.academy_id = a.id " +
            "WHERE a.email = :email", nativeQuery = true)
    List<Room> findAllByCustomerEmail(@Param("email") String email);

    @Query(value = "SELECT r.* FROM rooms r " +
            "JOIN dormitories d ON r.dormitory_id = d.id " +
            "JOIN academies a ON d.academy_id = a.id " +
            "WHERE (a.email = :email AND d.id = :dormitoryId)", nativeQuery = true)
    List<Room> findAllByCustomerEmailAndDormitory(@Param("email") String email, @Param("dormitoryId") Long dormitoryId);
}
