package com.example.demo.dto;

import org.springframework.beans.BeanUtils;

import com.example.demo.model.UserEntity;

import lombok.Getter;
import lombok.Setter;

//Data Transfer Object is for connecting front to back end

@Getter
@Setter
public class UserDto {

	private Long id;

	private String surName;

	private String firstName;

	private String surNameKana;

	private String firstNameKana;

	private String email;

	private String username;

	private String pass;

	private String gender;

	private String birthday;
	
	private RoleDto role;
	
	public UserEntity toEntity() {
		
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(this, entity);
		return entity;
		
	}

}
