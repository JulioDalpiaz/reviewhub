package com.juliodalpiaz.reviewhub.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.juliodalpiaz.reviewhub.dto.MediaSummary;
import com.juliodalpiaz.reviewhub.model.Media;
import com.juliodalpiaz.reviewhub.model.MediaType;

public interface MediaRepository extends JpaRepository<Media, UUID> {
  boolean existsByTitle(String title);

  @Query(value = """
      SELECT DISTINCT new com.juliodalpiaz.reviewhub.dto.MediaSummary(m.id, m.title, m.type, m.releaseYear)
      FROM Media m LEFT JOIN m.categories c
      WHERE (:type IS NULL OR m.type = :type) AND (:categoryId IS NULL OR c.id = :categoryId)
      """,
      countQuery = """
      SELECT COUNT(DISTINCT m)
      FROM Media m LEFT JOIN m.categories c
      WHERE (:type IS NULL OR m.type = :type) AND (:categoryId IS NULL OR c.id = :categoryId)
      """)
  Page<MediaSummary> findAllSummaries(@Param("type") MediaType type, @Param("categoryId") UUID categoryId, Pageable pageable);
}
