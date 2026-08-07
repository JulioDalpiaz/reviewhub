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
  @NotBlank @Size(max = 100) String title, 
  @NotNull MediaType type, 
  @NotNull @Min(1888) @Max(2100) Integer releaseYear, 
  @NotBlank @Size(max = 500) String synopsis, 
  @NotNull Set<UUID> categoryIds
) {}
