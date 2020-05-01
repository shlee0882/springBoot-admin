package com.example.test.repository;

import com.example.test.model.entity.OrderGroup;
import com.example.test.model.enumclass.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
    List<OrderGroup> findByStatusAndOrderTypeOrderByIdDesc(String status, OrderType orderType);
}
