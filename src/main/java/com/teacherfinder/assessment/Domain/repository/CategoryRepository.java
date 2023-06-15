package com.teacherfinder.assessment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teacherfinder.assessment.domain.model.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>{
    
}
