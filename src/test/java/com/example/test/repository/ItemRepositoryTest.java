package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Item;
import com.example.test.model.entity.Partner;
import com.example.test.model.enumclass.ItemStatus;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends TestApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setStatus(ItemStatus.UNREGISTERED);
        item.setName("삼성 노트북");
        item.setTitle("삼성 울트라 15인치");
        item.setContent("2020년형 노트북입니다.");
        item.setPrice(BigDecimal.valueOf(1000000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("partner01");

        Partner newPartner = new Partner();

        newPartner.setId(1L);
        item.setPartner(newPartner);

        Item newItem =  itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void create2(){
        Item item = new Item();
        item.setName("냉장고");
        item.setPrice(BigDecimal.valueOf(700000));
        item.setContent("LG 냉장고");

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void create3(){
        Item item = new Item();
        item.setName("세탁기");
        item.setPrice(BigDecimal.valueOf(500000));
        item.setContent("트롬 세탁기");

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertTrue(item.isPresent());
    }
}
