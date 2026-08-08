package com.juliodalpiaz.reviewhub.dto;

import java.util.Set;
import java.util.UUID;

import com.juliodalpiaz.reviewhub.model.MediaType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MediaRequest(
  @NotBlank(message="Title is required") @Size(max = 100, message = "Title must not exceed 100 characters") String title, 
  @NotNull(message="Type is required") MediaType type, 
  @NotNull(message="Year is required") @Min(value = 1888, message="Year must be at least 1888") @Max(value = 2100, message = "Year must be at most 2100") Integer releaseYear, 
  @NotBlank(message="Synopsis is required") @Size(max = 500, message = "Synopsis must not exceed 500 characters") String synopsis, 
  @NotNull(message = "Category list is required") Set<UUID> categoryIds
) {}
