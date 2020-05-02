package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.AdminUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.*;
import java.util.Optional;
import java.util.Random;

@Slf4j
public class AdminUserRepositoryTest extends TestApplicationTests {

    private Random random;

    @Autowired
    AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("adminUser02");
        adminUser.setPassword("adminUser02");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
/*        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("AdminServer");*/

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);

        newAdminUser.setAccount("CHANGE");
        // newAdminUser.setAccount("NEW");
        adminUserRepository.save(newAdminUser);
    }

    @Test
    public void createUsingBuilder(){
        String account = "adminUser03";
        String passWord = "adminUser03";
        String status = "UNREGISTERED";
        String role = "PARTNER";

        AdminUser adminUser = AdminUser.builder()
                .account(account)
                .password(passWord)
                .status(status)
                .role(role)
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
        Assert.assertEquals(newAdminUser.getAccount(), account);
    }

    @Test
    public void selectWhereId(){
        // where id = '1'
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findById(1L);
        optionalAdminUser.ifPresent(adminUser -> {
            log.info("{}",optionalAdminUser);
        });
        Assert.assertTrue(optionalAdminUser.isPresent());
    }

    @Test
    public void selectWhereAnd(){
        // where account = 'new' and status = 'REGISTERED'
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findByAccountAndStatus("NEW","REGISTERED");

        optionalAdminUser.ifPresent(adminUser -> {
            log.info("{}",optionalAdminUser);
        });
        Assert.assertTrue(optionalAdminUser.isPresent());
    }

    @Test
    public void selectMultipleIn(){
        List<String> accountList = new ArrayList<String>(Arrays.asList("NEW", "CHANGE"));
        List<String> statusList = new ArrayList<String>(Arrays.asList("REGISTERED", "UNREGISTERED"));

        // where account in ('CHANGE', 'NEW') and status in ('REGISTERED', 'UNREGISTERED')
        List<AdminUser> optionalAdminUser = adminUserRepository.findByAccountInAndStatusIn(accountList, statusList);
        log.info("{}",optionalAdminUser);
        Assert.assertNotNull(optionalAdminUser);
    }

    @Test
    public void selectWhereInOrderBy(){
        List<String> accountList = new ArrayList<String>(Arrays.asList("NEW", "CHANGE"));
        List<String> statusList = new ArrayList<String>(Arrays.asList("REGISTERED", "UNREGISTERED"));

        // where account in ('CHANGE', 'NEW') and status in ('REGISTERED', 'UNREGISTERED')
        List<AdminUser> optionalAdminUser = adminUserRepository.findByAccountInAndStatusInOrderByIdDesc(accountList, statusList);
        log.info("{}",optionalAdminUser);
        Assert.assertNotNull(optionalAdminUser);
    }

    @Test
    @Transactional
    public void updateAdminUser(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);
        log.info("변경 전 상태 : {}", adminUser);
        adminUser.ifPresent(u -> {
            u.setStatus("UNREGISTERED");
            AdminUser newAdminUser = adminUserRepository.save(u);
            log.info("변경 후 상태 : {}", newAdminUser);
            Assert.assertEquals(u.getStatus(), newAdminUser.getStatus());
        });
    }

    @Test
    @Transactional
    public void deleteAdminUser(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);
        log.info("{}", adminUser);
        adminUser.ifPresent(a -> {
            adminUserRepository.delete(a);
        });

        Optional<AdminUser> checkAdminUser = adminUserRepository.findById(1L);
        Assert.assertFalse(checkAdminUser.isPresent());
        log.info("{}", checkAdminUser);
    }

}
