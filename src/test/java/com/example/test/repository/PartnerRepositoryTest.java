package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Category;
import com.example.test.model.entity.Partner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Part;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class PartnerRepositoryTest extends TestApplicationTests {
    @Autowired
    PartnerRepository partnerRepository;

    @Test
    public void createNormal(){
        String name = "partner03";
        String status = "REGISTERED";
        String address = "서울시 성북구";
        String callCenter = "070-2241-5531";
        String partnerNumber = "010-2241-5531";
        String businessNumber = "123456789";
        String ceoName = "이순규";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 3L;

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
        Optional<Partner> partner =  partnerRepository.findById(1L);

        partner.ifPresent(p -> {
            log.info("{}", p);
            log.info("id : {}, address: {}, status :  {} , name: {}", p.getStatus(), p.getName(), p.getStatus(), p.getName());
            // {} argument 4개 이지만 받는건 2개이므로 2개만 표현됨.
            log.info("id : {}, address: {}", p.getStatus(), p.getName(), p.getCeoName(), p.getPartnerNumber());
        });
        Assert.assertNotNull(partner);
    }

    @Test
    public void readAll(){
        List<String> status = new ArrayList<>(Arrays.asList("REGISTERED","UNREGISTERED"));
        List<String> businessNumber = new ArrayList<>(Arrays.asList("123456789","987654321"));

        List<Partner> partnerList =  partnerRepository.findByStatusInAndBusinessNumberIn(status, businessNumber);

        List<Partner> newPartnerList =
        partnerList.stream()
                .map(partner -> {
                    partner.setStatus("UNREGISTERED");
                    partner.setCeoName("박초아");
                    return partner;
                })
                .filter(partner -> !"UNREGISTERED".equals(partner.getStatus()))
                .collect(Collectors.toList());
        // return : []
        log.info("{}", newPartnerList);
    }

    @Test
    @Transactional
    public void update(){
        List<Partner> partnerList = partnerRepository.findAll();

        log.info("변경 전 데이터 : {}", partnerList);

        List<Partner> newPartnerList =
        partnerList.stream()
                .map(partner -> {
                    partner.setName("이상현");
                    partner.setAddress("서울시 강서구");
                    return partner;
                })
                .filter(partner -> !"조현영".equals(partner.getCeoName()))
                .map(partner -> partnerRepository.save(partner))
                .collect(Collectors.toList());

        log.info("변경 후 데이터 : {}",newPartnerList);
    }

    @Test
    @Transactional
    public void deletePartner(){
        Optional<Partner> partner = partnerRepository.findById(2L);
        partner.ifPresent(p -> {
            partnerRepository.delete(p);
        });

        Optional<Partner> checkPartner = partnerRepository.findById(2L);
        Assert.assertFalse(checkPartner.isPresent());
    }
}
