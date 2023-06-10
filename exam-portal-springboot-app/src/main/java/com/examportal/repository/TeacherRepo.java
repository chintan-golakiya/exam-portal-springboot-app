package com.examportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.TeacherEntity;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Integer>  {
	Optional<TeacherEntity> findByEmail(String email);
	boolean existsByEmail(String email);
}
