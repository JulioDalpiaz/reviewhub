package com.juliodalpiaz.reviewhub.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliodalpiaz.reviewhub.model.Media;

public interface MediaRepository extends JpaRepository<Media, UUID> {

}
