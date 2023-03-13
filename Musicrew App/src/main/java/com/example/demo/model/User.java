package com.example.demo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class User {
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
	@Email
	@Size(max = 254)
	private String emailConfirm;

	@NotBlank
	@Size(min = 6, max= 60)
	private String password;
	
	@NotBlank
	@Size(min = 6, max= 60)
	private String passwordConfirm;

	private String gender;

	private String birthday;

	private String displayName;

}
