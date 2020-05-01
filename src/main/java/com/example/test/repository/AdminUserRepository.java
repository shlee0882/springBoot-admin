package com.example.test.repository;

import com.example.test.model.entity.AdminUser;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> { // id에 해당하는 Long type
    Optional<AdminUser> findByAccountAndStatus(String account, String status);
    List<AdminUser> findByAccountInAndStatusIn(List<String> account, List<String> Status);
    List<AdminUser> findByAccountInAndStatusInOrderByIdDesc(List<String> account, List<String> Status);
}
