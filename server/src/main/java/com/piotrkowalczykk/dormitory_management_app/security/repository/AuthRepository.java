package com.piotrkowalczykk.dormitory_management_app.security.repository;

import com.piotrkowalczykk.dormitory_management_app.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

}
