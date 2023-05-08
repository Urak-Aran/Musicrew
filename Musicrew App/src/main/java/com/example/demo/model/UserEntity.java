package com.example.demo.model;

import org.springframework.beans.BeanUtils;

import com.example.demo.dto.UserDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 60)
	private String surName;

	@NotBlank
	@Size(max = 60)
	private String firstName;

	@NotBlank
	@Size(max = 60)
	private String surNameKana;

	@NotBlank
	@Size(max = 60)
	private String firstNameKana;

	@NotBlank
	@Email
	@Size(max = 254)
	private String email;

	@NotBlank
	@Size(min = 6, max = 60)
	private String username;

	@NotBlank
	@Size(min = 6, max = 60)
	private String pass;

	private String gender;

	private String birthday;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "role", referencedColumnName = "id")
	private RoleEntity role;
	
	public UserDto toDto() {
		
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(this, dto);
		return dto;
		
	}
}






