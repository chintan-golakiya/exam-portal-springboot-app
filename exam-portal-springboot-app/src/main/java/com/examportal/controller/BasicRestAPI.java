package com.examportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class BasicRestAPI {
	@GetMapping("/")
	public ResponseEntity<String> publicHome(){
		return new ResponseEntity<String>("Ok",HttpStatus.OK);
	}
}
