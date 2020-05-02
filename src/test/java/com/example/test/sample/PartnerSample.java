package com.example.test.sample;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Category;
import com.example.test.model.entity.Partner;
import com.example.test.repository.CategoryRepository;
import com.example.test.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class PartnerSample extends TestApplicationTests {

    private Random random;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createSample(){
        random = new Random();
        List<Category> categoryList = categoryRepository.findAll();

        for(int i = 0; i < categoryList.size(); i++){
            Category category = categoryList.get(i);

            for(int j = 1; j < 10; j++){

                // 가입 상태 랜덤
                int div = (random.nextInt(10)+1) % 2;
                String status = (div == 0 ? "REGISTERED" : "UNREGISTERED");

                Partner partner = Partner.builder()
                        .category(category)
                        .name(category.getTitle()+j+" 호점")
                        .status(status)
                        .address("서울시 강남구 "+j+"번길"+random.nextInt(100)+1+"호")
                        .callCenter("070-"+String.format("%04d", random.nextInt(100)+1)+"-"+String.format("%04d", random.nextInt(100)+1))
                        .partnerNumber("010-1111-"+String.format("%04d", i))
                        .businessNumber((random.nextInt(999999999)+1)+""+j)
                        .ceoName(j+" 대표")
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals("UNREGISTERED") ? getRandomDate() : null )
                        .build();

                log.info("{}",partner);
                partnerRepository.save(partner);
            }
        }
    }

    @Test
    public void createTest2(){
        for(int j=0; j< 10; j++){
            String a = String.format("%04d", j);
            log.info("{}", a);
        }
        Random random = new Random();
        Integer randomIntValue = random.nextInt(9999)+1;

    }

    @Test
    public void createTest(){
        random = new Random();
        List<String> addressList = new ArrayList(
                Arrays.asList("강남구", "강서구", "마포구", "구로구", "성북구", "광진구", "동작구", "관악구", "송파구", "종로구"));
        List<String> statusList = new ArrayList<>(Arrays.asList("REGISTERED", "UNREGISTERED"));

        List<Category> categoryList = categoryRepository.findAll();
        for(Category category : categoryList){
            for(int j=0; j< 10; j++){
                String address= addressList.get(random.nextInt(addressList.size()));
                String status = statusList.get(random.nextInt(statusList.size()));

                Partner partner = Partner.builder()
                        .category(category)
                        .name(category.getTitle()+" "+j+"호점")
                        .status(status)
                        .address("서울시 "+address+" "+setRandomInt(1,100)+"번길 "+setRandomInt(1,100)+"호")
                        .callCenter("070-"+String.format("%04d", setRandomInt(1,100))+"-"+String.format("%04d", setRandomInt(1,100)))
                        .partnerNumber("010-"+String.format("%04d", setRandomInt(1,9999))+"-"+String.format("%04d", setRandomInt(1,9999)))
                        .businessNumber(setRandomInt(1,9999)+""+j)
                        .ceoName(j+" 대표")
                        .registeredAt(getRandomDate())
                        .unregisteredAt(status.equals("UNREGISTERED") ? getRandomDate() : null )
                        .build();

                log.info("{}", partner);
                partnerRepository.save(partner);
            }
        }
    }


    private LocalDateTime getRandomDate(){
        return LocalDateTime.of(2020,getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber(),getRandomNumber());
    }

    private int getRandomNumber(){
        return random.nextInt(11)+1;
    }

    private int setRandomInt(int min, int max){
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
