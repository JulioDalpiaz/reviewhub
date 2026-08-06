package com.juliodalpiaz.reviewhub.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliodalpiaz.reviewhub.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
  boolean existsByName(String name);
}
