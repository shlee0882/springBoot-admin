package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Category;
import com.example.test.model.entity.Partner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PartnerRepositoryTest extends TestApplicationTests {
    @Autowired
    PartnerRepository partnerRepository;

    @Test
    public void create(){
        String name = "partner01";
        String status = "REGISTERED";
        String address = "서울시 강남구";
        String callCenter = "070-2013-0882";
        String partnerNumber = "010-2013-0882";
        String businessNumber = "123456789";
        String ceoName = "이상현";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();

        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createdBy);
        // partner.setCategoryId(categoryId);

        Category newCategory = new Category();
        newCategory.setId(categoryId);
        partner.setCategory(newCategory);

        Partner newPartner =  partnerRepository.save(partner);
        Assert.assertNotNull(newPartner);
        Assert.assertEquals(newPartner.getName(), name); // 동일한지
    }

    @Test
    public void read(){

    }
}
