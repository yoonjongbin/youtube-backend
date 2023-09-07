package com.kh.youtube.repository;


import com.kh.youtube.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryDAO extends JpaRepository<Category, Integer> {
}
