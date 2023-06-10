package com.examportal.dto;

class TeacherDetails {
	private String username;
	private String email;
	private Integer id;
	public TeacherDetails(String username, String email, Integer id) {
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

public class TeacherLoginResponseDto {
	private boolean success;
	private String message;
	private String token;
	private TeacherDetails teacher;
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
	public TeacherDetails getTeacher() {
		return teacher;
	}
	public void setTeacher(String username, String email, Integer id) {
		this.teacher = new TeacherDetails(username, email, id);
	}
	
	
}
