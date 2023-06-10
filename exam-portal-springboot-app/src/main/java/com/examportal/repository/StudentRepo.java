package com.examportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {
	Optional<StudentEntity> findByEmail(String email);
	boolean existsByEmail(String email);
}
