package com.juliodalpiaz.reviewhub.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juliodalpiaz.reviewhub.dto.CategoryRequest;
import com.juliodalpiaz.reviewhub.dto.CategoryResponse;
import com.juliodalpiaz.reviewhub.exception.ResourceNotFoundException;
import com.juliodalpiaz.reviewhub.model.Category;
import com.juliodalpiaz.reviewhub.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
  public final CategoryRepository categoryRepository;

  public List<CategoryResponse> listAllCategories(){
    return categoryRepository.findAll().stream()
      .map(CategoryResponse::from)
      .toList();
  }

  public CategoryResponse addCategory(CategoryRequest categoryRequest){
    return CategoryResponse.from(categoryRepository.save(
      Category.builder()
      .name(categoryRequest.name())
    .build()));
  }

  @Transactional
  public CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest){
    Category existing = categoryRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    existing.setName(categoryRequest.name());
    return CategoryResponse.from(existing);
  }

  @Transactional
  public void deleteCategory(UUID id){
    Category existing = categoryRepository.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    existing.getMedias().forEach(m -> m.getCategories().remove(existing));
    categoryRepository.delete(existing);
  }
}
