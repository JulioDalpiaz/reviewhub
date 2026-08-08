package com.juliodalpiaz.reviewhub.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.juliodalpiaz.reviewhub.dto.MediaRequest;
import com.juliodalpiaz.reviewhub.dto.MediaResponse;
import com.juliodalpiaz.reviewhub.dto.MediaSummary;
import com.juliodalpiaz.reviewhub.model.MediaType;
import com.juliodalpiaz.reviewhub.service.MediaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
@Validated
public class MediaController {
  private final MediaService mediaService;

  @GetMapping
  public Page<MediaSummary> listAllMedias(@RequestParam(required = false) MediaType type, @RequestParam(required = false) UUID categoryId, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @PageableDefault(size=10, sort="title") Pageable pageable){
    if (page != null && page < 0)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page must be at least 0");
    if (size != null && size < 1)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "size must be at least 1");

    return mediaService.listAllMedias(type, categoryId, pageable);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MediaResponse> getMediaById(@PathVariable UUID id){
    return new ResponseEntity<>(mediaService.getMediaById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MediaResponse> addMedia(@RequestBody @Valid MediaRequest req){
    return new ResponseEntity<>(mediaService.addMedia(req), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<MediaResponse> updateMedia(@PathVariable UUID id, @RequestBody @Valid MediaRequest req){
    return new ResponseEntity<>(mediaService.updateMedia(id, req), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedia(@PathVariable UUID id){
    mediaService.deleteMedia(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
