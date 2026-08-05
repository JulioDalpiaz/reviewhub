package com.juliodalpiaz.reviewhub.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliodalpiaz.reviewhub.model.Review;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
  boolean existsByUserIdAndMediaId(UUID userId, UUID mediaId);
}
