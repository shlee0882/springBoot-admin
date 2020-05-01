package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Item;
import com.example.test.model.entity.User;
import com.example.test.model.enumclass.UserStatus;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends TestApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "test01";
        String password = "test01";
        String status = "UNREGISTERED";
        String email = "shlee0882@gmail.com";
        String phoneNumber = "010-2013-0882";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(UserStatus.REGISTERED);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
                .status(UserStatus.REGISTERED)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();



        User newUser = userRepository.save(u);
        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-2013-0882");

        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("--------주문 묶음-------");
                System.out.println("수령인: " + orderGroup.getRevName());
                System.out.println("수령지: " + orderGroup.getRevAddress());
                System.out.println("총금액: " + orderGroup.getTotalPrice());
                System.out.println("총수량: " + orderGroup.getTotalQuantity());

                System.out.println("--------주문 상세-------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 :" + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 :" + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객 센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
                });

            });

            Assert.assertNotNull(user);
        }
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("modUser1");
            selectUser.setEmail("modUser1@gmail.com");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("shlee0882");
            User newUser = userRepository.save(selectUser);
            System.out.println("user: "+newUser);
        });
    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());    // true
        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent());    // false
    }


}