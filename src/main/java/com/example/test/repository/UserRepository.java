package com.example.test.repository;

import com.example.test.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    // Optional은 있을수도 없을 수도 있다의 개념
    // select * from user where account = ? << user1, user2
    Optional<User> findByAccount(String account);

    // select * from user where email = ?
    Optional<User> findByEmail(String email);

    // select * from user where account = ?  and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}