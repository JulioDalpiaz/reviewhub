package com.juliodalpiaz.reviewhub.controller;

import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.juliodalpiaz.reviewhub.dto.MediaRequest;
import com.juliodalpiaz.reviewhub.dto.MediaResponse;
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
  public ResponseEntity<List<MediaResponse>> listAllMedias(){
    return new ResponseEntity<>(mediaService.listAllMedias(), HttpStatus.OK);
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
