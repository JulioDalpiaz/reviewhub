package com.juliodalpiaz.reviewhub.dto;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.juliodalpiaz.reviewhub.model.Media;
import com.juliodalpiaz.reviewhub.model.MediaType;

public record MediaResponse(UUID id, String title, MediaType type, Integer releaseYear, String synopsis, Set<CategoryResponse> categories) {
  public static MediaResponse from(Media media){
    return new MediaResponse(media.getId(), media.getTitle(), media.getType(), media.getReleaseYear(), media.getSynopsis(), media.getCategories().stream().map(CategoryResponse::from).collect(Collectors.toSet()));
  }
}
