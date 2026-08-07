package com.juliodalpiaz.reviewhub.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "media")
public class Media {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String title;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private MediaType type;
  @Column(name = "release_year", nullable = false)
  private Integer releaseYear;
  @Column(nullable = false)
  private String synopsis;

  @ManyToMany
  @JoinTable(
    name = "media_category",
    joinColumns = @JoinColumn(name = "media_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  
  @Builder.Default
  private Set<Category> categories = new HashSet<>();

  @Builder.Default
  @OneToMany(mappedBy = "media")
  private Set<Review> reviews = new HashSet<>();
}
