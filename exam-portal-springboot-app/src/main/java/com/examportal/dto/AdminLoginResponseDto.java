package com.examportal.dto;

class AdminDetails {
	String usename;
	int id;
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}

public class AdminLoginResponseDto {
	private boolean success;
	private String message;
	private String token;
	private AdminDetails admin;
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
	public AdminDetails getAdmin() {
		return admin;
	}
	public void setAdmin(String username, Integer id) {
		this.admin = new AdminDetails();
		this.admin.setUsename(username);
		this.admin.setId(id);
	}
	
}
