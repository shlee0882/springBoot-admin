package com.example.test.service;

import com.example.test.controller.CrudController;
import com.example.test.ifs.CrudInterface;
import com.example.test.model.entity.OrderGroup;
import com.example.test.model.network.Header;
import com.example.test.model.network.request.OrderGroupApiRequest;
import com.example.test.model.network.response.OrderGroupApiResponse;
import com.example.test.repository.OrderGroupRepository;
import com.example.test.repository.UserRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();
        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    return response(orderGroup);
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body =  request.getData();

        Optional<OrderGroup> optional = baseRepository.findById(body.getId());
        return
            optional.map(orderGroup -> {
                orderGroup.setStatus(body.getStatus())
                        .setOrderType(body.getOrderType())
                        .setRevAddress(body.getRevAddress())
                        .setRevName(body.getRevName())
                        .setPaymentType(body.getPaymentType())
                        .setTotalPrice(body.getTotalPrice())
                        .setTotalQuantity(body.getTotalQuantity())
                        .setOrderAt(body.getOrderAt())
                        .setArrivalDate(body.getArrivalDate())
                        .setUser(userRepository.getOne(body.getUserId()));
                return orderGroup;
            })
                    .map(changeOrderGroup -> baseRepository.save(changeOrderGroup))
                    .map(updateOrderGroup -> response(updateOrderGroup))
                    .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(body);
    }
}
