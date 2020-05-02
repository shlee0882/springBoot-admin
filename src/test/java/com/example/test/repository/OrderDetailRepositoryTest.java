package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Item;
import com.example.test.model.entity.OrderDetail;
import com.example.test.model.entity.OrderGroup;
import com.example.test.model.entity.User;
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
public class OrderDetailRepositoryTest extends TestApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail= new OrderDetail();

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(1000000));

        // orderDetail.setOrderGroupId(1L);    // 어떠한 장바구니에
        // orderDetail.setItemId(1L);              // 어떠한 상품

        OrderGroup newOrderGroup = new OrderGroup();
        newOrderGroup.setId(1L);

        Item newItem = new Item();
        newItem.setId(1L);

        orderDetail.setOrderGroup(newOrderGroup);
        orderDetail.setItem(newItem);

        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(newOrderDetail);
    }

    @Test
    public void createAnother(){

        String status = "WAITING";
        Integer quantity = 1;
        BigDecimal totalPrice = BigDecimal.valueOf(2000000);

        OrderGroup orderGroup = new OrderGroup().setId(2L);
        Item item = new Item().setId(2L);

        OrderDetail orderDetail = OrderDetail.builder()
                .status(status)
                .arrivalDate(LocalDateTime.now().plusDays(2))
                .quantity(quantity)
                .totalPrice(totalPrice)
                .orderGroup(orderGroup)
                .item(item)
                .build();

        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(saveOrderDetail);
        log.info("저장된 값 : {}", saveOrderDetail);
    }

    @Test
    public void readOrderDetail(){
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();

        Assert.assertNotNull(orderDetailList);
        log.info("주문상세 리스트 : {}", orderDetailList);
    }

    @Test
    @Transactional
    public void updateOrderDetail(){
        Optional<OrderDetail> orderDetailOne =  orderDetailRepository.findById(1L);
        log.info("변경 전 값 : {}", orderDetailOne);

        orderDetailOne.ifPresent(orderDetail -> {
            orderDetail.setQuantity(5);
            OrderDetail updateOrderDetail = orderDetailRepository.save(orderDetail);
            Assert.assertEquals(orderDetail.getQuantity(), updateOrderDetail.getQuantity());
            log.info("변경 후 값 : {}", updateOrderDetail);
        });
    }

    @Test
    @Transactional
    public void deleteOrderDetail(){
        Optional<OrderDetail> orderDetailOne = orderDetailRepository.findById(1L);
        log.info("주문상세 : {}", orderDetailOne);
        orderDetailOne.ifPresent(orderDetail ->{
            orderDetailRepository.delete(orderDetail);
        });
        Optional<OrderDetail> checkOrderDetail = orderDetailRepository.findById(1L);
        log.info("삭제 체크 : {}", checkOrderDetail);
    }
}
