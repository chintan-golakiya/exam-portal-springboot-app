package com.examportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Integer> {
	Optional<AdminEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
}
