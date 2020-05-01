package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.AdminUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        List<AdminUser> optionalAdminUser = adminUserRepository.findByAccountInAndStatusInOrderById(accountList, statusList);
        log.info("{}",optionalAdminUser);
        Assert.assertNotNull(optionalAdminUser);
    }



}
