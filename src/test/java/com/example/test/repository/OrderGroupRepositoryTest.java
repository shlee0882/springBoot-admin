package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.OrderGroup;
import com.example.test.model.entity.User;
import com.example.test.model.enumclass.OrderType;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class OrderGroupRepositoryTest extends TestApplicationTests {
    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType(OrderType.ALL);
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("이상현");
        orderGroup.setTotalPrice(BigDecimal.valueOf(1000000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());

        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("AdminServer");
        User newUser = new User();
        newUser.setId(1L);
        orderGroup.setUser(newUser);
        // orderGroup.setUserId(1L);

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOrderGroup);
    }

    @Test
    public void createStream(){
        String status = "COMPLETE";
        String revAddress = "서울시 마포구";
        String revName = "조현영";
        BigDecimal totalPrice = BigDecimal.valueOf(200000);
        int totalQuantity = 5;
        User user = userRepository.findFirstById(1L);

        OrderGroup orderGroup = OrderGroup.builder()
                .status(status)
                .orderType(OrderType.ALL)
                .revAddress(revAddress)
                .revName(revName)
                .totalPrice(totalPrice)
                .totalQuantity(totalQuantity)
                .orderAt(LocalDateTime.now())
                .arrivalDate(LocalDateTime.now().plusDays(3))
                .user(user)
                .build();

        log.info("{}", orderGroup);
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        Assert.assertNotNull(newOrderGroup);
    }

    @Test
    public void selectOrderGroup(){
        List<OrderGroup> orderGroupList = orderGroupRepository.findAll();

        List<OrderGroup> newOrderGroupList =
        orderGroupList.stream()
                .filter(orderGroup -> orderGroup.getTotalQuantity() < 5)
                .collect(Collectors.toList());

        log.info("AllList : {}", orderGroupList);
        log.info("filterApplied : {}", newOrderGroupList);
        Assert.assertNotNull(orderGroupList);
    }

    @Test
    public void selectMultiple(){
        String status = "COMPLETE";
        OrderType orderType = OrderType.ALL;
        List<OrderGroup> orderGroupList = orderGroupRepository.findByStatusAndOrderTypeOrderByIdDesc(status, orderType);
        log.info("{}", orderGroupList);
        Assert.assertNotNull(orderGroupList);
    }

    @Test
    @Transactional
    public void updateOrderGroup(){
        Optional<OrderGroup> orderGroupOne =  orderGroupRepository.findById(1L);

        orderGroupOne.ifPresent(orderGroup -> {
            orderGroup.setRevAddress("서울시 구로구");
            orderGroup.setRevName("루피");
            OrderGroup updateOrderGroup = orderGroupRepository.save(orderGroup);
            log.info("{}", updateOrderGroup);
            Assert.assertEquals(orderGroup.getRevAddress(), updateOrderGroup.getRevAddress());
            Assert.assertEquals(orderGroup.getRevName(), updateOrderGroup.getRevName());
        });
    }

    @Test
    @Transactional
    public void deleteOrderGroup(){
        Optional<OrderGroup> orderGroupOne = orderGroupRepository.findById(1L);
        log.info("select Check : {}", orderGroupOne);
        orderGroupOne.ifPresent(orderGroup -> {
            orderGroupRepository.delete(orderGroup);
        });

        Optional<OrderGroup> checkDeleteOrderGroup = orderGroupRepository.findById(1L);
        log.info("delete Check : {}", checkDeleteOrderGroup);
        Assert.assertFalse(checkDeleteOrderGroup.isPresent());

    }
}
