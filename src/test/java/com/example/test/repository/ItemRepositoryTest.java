package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Item;
import com.example.test.model.entity.Partner;
import com.example.test.model.enumclass.ItemStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ItemRepositoryTest extends TestApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){

        Partner newPartner = new Partner();
        newPartner.setId(1L);

        Item item = Item.builder()
                .status(ItemStatus.UNREGISTERED)
                .name("삼성 노트북")
                .title("삼성 울트라 15인치")
                .content("2020년형 노트북입니다.")
                .price(BigDecimal.valueOf(1000000))
                .brandName("삼성")
                .registeredAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .createdBy("partner01")
                .partner(newPartner)
                .build();

        Item newItem =  itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }

    @Test
    public void read(){
        List<Item> itemList =  itemRepository.findAll();
        for (Item item: itemList) {
            log.info("{}",item);
        }
    }

    @Test
    @Transactional
    public void updateItem(){
        Optional<Item> item = itemRepository.findById(1L);
        log.info("변경 전 상태 : {}", item);
        item.ifPresent(i->{
            i.setStatus(ItemStatus.REGISTERED);
            Item updateItem = itemRepository.save(i);
            Assert.assertEquals(i.getStatus(), updateItem.getStatus());
            log.info("변경 후 상태 : {}", updateItem);
        });
    }

    @Test
    @Transactional
    public void deleteItem(){
        Optional<Item> item = itemRepository.findById(1L);
        log.info("데이터 : {}", item);
        item.ifPresent(i ->{
            itemRepository.delete(i);
        });
        Optional<Item> checkItem = itemRepository.findById(1L);
        Assert.assertFalse(checkItem.isPresent());
        log.info("삭제 되었는지 체크 : {}", checkItem);

    }
}
