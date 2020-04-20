package com.example.test.repository;

import com.example.test.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> { // id에 해당하는 Long type

}
