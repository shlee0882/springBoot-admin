package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Item;
import com.example.test.model.entity.OrderDetail;
import com.example.test.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends TestApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail= new OrderDetail();

        orderDetail.setOrderDt(LocalDateTime.now());

        // orderDetail.setUserId(2L);
        // orderDetail.setItemId(1L);

        User user = new User();
        Item item = new Item();

        user.setId(2L);
        item.setId(3L);

        orderDetail.setUser(user);
        orderDetail.setItem(item);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(newOrderDetail);
    }
}
