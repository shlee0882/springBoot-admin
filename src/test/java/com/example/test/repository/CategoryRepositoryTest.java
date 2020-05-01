package com.example.test.repository;

import com.example.test.TestApplicationTests;
import com.example.test.model.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class CategoryRepositoryTest extends TestApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void createComputer(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminUser";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);

    }

    @Test
    public void createNintendo(){
        String type = "GAME MACHINE";
        String title = "닌텐도";

        Category category = Category.builder()
                .title(title)
                .type(type)
                .build();

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
    }

    @Test
    public void createSmartPhone(){
        String type = "SMART PHONE";
        String title = "갤럭시노트10";

        Category category = Category.builder()
                .title(title)
                .type(type)
                .build();

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
    }

    @Test
    public void read(){
        // Optional<Category> optionalCategory = categoryRepository.findById(1L);
        String title = "컴퓨터";
        String type = "COMPUTER";

        Optional<Category> optionalCategory = categoryRepository.findByType(type);
        Optional<Category>  optionalCateyoryTitleAndType = categoryRepository.findByTitleAndType(title, type);
        // select * from category where type = ''COMPUTER'
        optionalCategory.ifPresent(c->{

            Assert.assertEquals(c.getType(), type);
            log.info("{}", c.getId());
            log.info("{}", c.getType());
            log.info("{}", c.getTitle());
        });
    }

    @Test
    public void readNintendo(){
        String title = "닌텐도";
        String type = "GAME MACHINE";

        Optional<Category>  optionalCateyoryTitleAndType = categoryRepository.findByTitleAndType(title, type);
        // select * from category where title = '닌텐도' and type = 'GAME MACHINE'
        optionalCateyoryTitleAndType.ifPresent(c->{
            log.info("{}", c);
            Assert.assertEquals(c.getTitle(), title);
        });
    }

    @Test
    public void updateNintendo(){
        String type = "GAME MACHINE";
        Optional<Category> category = categoryRepository.findByType(type);

        category.ifPresent(c -> {
            c.setTitle("닌텐도2");
            c.setType("GAME LAUNCHER");

            Category newCategory = categoryRepository.save(c);
            Assert.assertEquals(newCategory.getTitle(), c.getTitle());
        });
    }

    @Test
    @Transactional
    public void deleteSmartPhone(){
        String type = "SMART PHONE";
        Optional<Category> category = categoryRepository.findByType(type);

        category.ifPresent(c -> {
            categoryRepository.delete(c);
        });
        Optional<Category> researchCategory = categoryRepository.findByType(type);
        Assert.assertFalse(researchCategory.isPresent());

    }

}
