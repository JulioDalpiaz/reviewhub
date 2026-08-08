package com.juliodalpiaz.reviewhub.dto;

import java.util.UUID;

import com.juliodalpiaz.reviewhub.model.MediaType;

public record MediaSummary(UUID id, String title, MediaType type, Integer releaseYear) {}
