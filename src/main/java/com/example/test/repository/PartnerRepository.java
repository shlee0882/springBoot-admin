package com.example.test.repository;

import com.example.test.model.entity.Category;
import com.example.test.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByCategory(Category category);
}
