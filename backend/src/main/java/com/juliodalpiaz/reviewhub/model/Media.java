package com.juliodalpiaz.reviewhub.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "media")
public class Media {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String title;
  @Column(nullable = false)
  private String type;
  @Column(name = "release_year", nullable = false)
  private int releaseYear;
  @Column(nullable = false)
  private String synopsis;

  @ManyToMany
  @JoinTable(
    name = "media_category",
    joinColumns = @JoinColumn(name = "media_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  private Set<Category> categories = new HashSet<>();

  @OneToMany(mappedBy = "media")
  private Set<Review> reviews = new HashSet<>();
}
