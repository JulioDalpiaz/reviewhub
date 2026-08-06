package com.juliodalpiaz.reviewhub.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliodalpiaz.reviewhub.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
