package com.juliodalpiaz.reviewhub.dto;

import java.util.UUID;

import com.juliodalpiaz.reviewhub.model.Category;

public record CategoryResponse(UUID id, String name) {
  public static CategoryResponse from(Category category){
    return new CategoryResponse(category.getId(), category.getName());
  }
}
