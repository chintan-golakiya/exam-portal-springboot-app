package com.examportal.dto;

class StudentDetails {
	private String username;
	private String email;
	private Integer id;
	public StudentDetails(String username, String email, Integer id) {
		this.username = username;
		this.email = email;
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}

public class StudentLoginResponseDto {
	private boolean success;
	private String message;
	private String token;
	private StudentDetails student;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public StudentDetails getStudent() {
		return student;
	}
	public void setStudent(String username, String email, Integer id) {
		this.student = new StudentDetails(username, email, id);
	}
	
	
}
