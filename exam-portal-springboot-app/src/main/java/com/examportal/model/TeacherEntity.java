package com.examportal.model;

import javax.validation.constraints.Email;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="teacher")
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Email
	private String email;
	private String password;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="createdBy", referencedColumnName = "id")
	private AdminEntity createdBy;
	
	public AdminEntity getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(AdminEntity createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	
}
