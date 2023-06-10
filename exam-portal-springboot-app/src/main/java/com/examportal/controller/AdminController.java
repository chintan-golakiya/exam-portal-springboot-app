package com.examportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.dto.SuccessandMessageDto;
import com.examportal.dto.TeacherRegisterDto;
import com.examportal.model.TeacherEntity;
import com.examportal.repository.AdminRepo;
import com.examportal.repository.TeacherRepo;
import com.examportal.security.JwtGenerator;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
	
	@Autowired
	private JwtGenerator jwtGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TeacherRepo teacherRepo;
	@Autowired
	private AdminRepo adminRepo;

	@PostMapping("/register")
	public ResponseEntity<SuccessandMessageDto> registerTeacher(@RequestBody TeacherRegisterDto teacherRegisterDto, @RequestHeader(name="Authorization") String token) {
		SuccessandMessageDto response = new SuccessandMessageDto();
		if(teacherRepo.existsByEmail(teacherRegisterDto.getEmail())) {
			response.setMessage("Email is already registered !!");
			response.setSuccess(false);
			return new ResponseEntity<SuccessandMessageDto>(response, HttpStatus.BAD_REQUEST);
		}
		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setName(teacherRegisterDto.getUsername());
		teacherEntity.setPassword(passwordEncoder.encode(teacherRegisterDto.getPassword()));
		teacherEntity.setEmail(teacherRegisterDto.getEmail());
		teacherEntity.setStatus(true);
		try {
			teacherEntity.setCreatedBy(adminRepo.findByUsername(jwtGenerator.getUsernameFromJWT(token.substring(7))).orElseThrow());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			response.setMessage("Unauthorized request");
			response.setSuccess(false);
			return new ResponseEntity<SuccessandMessageDto>(response, HttpStatus.UNAUTHORIZED);
		}
		teacherRepo.save(teacherEntity);
		response.setMessage("Profile Created Successfully !!");
		response.setSuccess(true);
		return new ResponseEntity<SuccessandMessageDto>(response, HttpStatus.OK);
	}
}
