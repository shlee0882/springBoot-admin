package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Item;
import com.example.test.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends TestApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("user1");
        user.setEmail("user1@gmail.com");
        user.setPhoneNumber("010-1111-2222");
        user.setRegDt(LocalDateTime.now());
        user.setRegUser("shlee0882");

        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }

    @Test
    @Transactional
    public void read(){
        // select * from user where id = ?
        // Optional<User> user = userRepository.findById(2L);
        Optional<User> user = userRepository.findByAccount("user2");

        user.ifPresent(selectUser ->{
            System.out.println("user: "+selectUser);
            selectUser.getOrderDetailList().stream().forEach(detail->{
                Item item= detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("modUser1");
            selectUser.setEmail("modUser1@gmail.com");
            selectUser.setModDt(LocalDateTime.now());
            selectUser.setModUser("shlee0882");
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