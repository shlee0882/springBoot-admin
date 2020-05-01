package com.example.test.repository;

import com.example.test.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // 1건에 대해서 가장 최근것이 리턴됨.
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
    User findFirstById(Long id);
}