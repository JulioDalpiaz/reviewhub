package com.juliodalpiaz.reviewhub.service;

import com.juliodalpiaz.reviewhub.repository.CategoryRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.juliodalpiaz.reviewhub.dto.MediaRequest;
import com.juliodalpiaz.reviewhub.dto.MediaResponse;
import com.juliodalpiaz.reviewhub.exception.ResourceNotFoundException;
import com.juliodalpiaz.reviewhub.model.Category;
import com.juliodalpiaz.reviewhub.model.Media;
import com.juliodalpiaz.reviewhub.repository.MediaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MediaService {
  private final MediaRepository mediaRepository;
  private final CategoryRepository categoryRepository;

  private Set<Category> resolveCategories(Set<UUID> categoryIds) {
    return categoryIds.stream()
      .map(categoryId -> categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found " + categoryId)))
      .collect(Collectors.toSet());
  }

  @Transactional(readOnly = true)
  public List<MediaResponse> listAllMedias(){
    return mediaRepository.findAll().stream()
      .map(MediaResponse::from)
      .toList();
  }

  @Transactional(readOnly = true)
  public MediaResponse getMediaById(UUID id){
    Media existing = mediaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Media not found " + id));

    return MediaResponse.from(existing);
  }

  public MediaResponse addMedia(MediaRequest req){
    if (mediaRepository.existsByTitle(req.title()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Media with name '" + req.title() + "' already exists");

    return MediaResponse.from(mediaRepository.save(
      Media.builder()
        .title(req.title())
        .type(req.type())
        .releaseYear(req.releaseYear())
        .synopsis(req.synopsis())
        .categories(resolveCategories(req.categoryIds()))
      .build()
    ));
  }

  @Transactional
  public MediaResponse updateMedia(UUID id, MediaRequest req){
    Media existing = mediaRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Media not found " + id));

    if (!existing.getTitle().equals(req.title()) && mediaRepository.existsByTitle(req.title()))
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Media with name '" + req.title() + "' already exists");

    existing.setTitle(req.title());
    existing.setType(req.type());
    existing.setReleaseYear(req.releaseYear());
    existing.setSynopsis(req.synopsis());
    existing.setCategories(resolveCategories(req.categoryIds()));

    return MediaResponse.from(existing);
  }

  @Transactional
  public void deleteMedia(UUID id){
    Media existing = mediaRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Media not found " + id));
    
    mediaRepository.delete(existing);
  }
}
