package com.juliodalpiaz.reviewhub.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.juliodalpiaz.reviewhub.dto.CategoryRequest;
import com.juliodalpiaz.reviewhub.dto.CategoryResponse;
import com.juliodalpiaz.reviewhub.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> listAllCategories(){
    return new ResponseEntity<>(categoryService.listAllCategories(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest categoryDto){
    return new ResponseEntity<>(categoryService.addCategory(categoryDto), HttpStatus.CREATED);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryRequest categoryDto){
    return new ResponseEntity<>(categoryService.updateCategory(id, categoryDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
    categoryService.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
