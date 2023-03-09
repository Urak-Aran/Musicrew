package com.example.demo.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;


@Data
public class User {
	@NotBlank
	@Size(max = 60)
	private String surName;
	private String firstName;

	private String surNameKana;
	private String firstNameKana;

	@NotBlank
	@Email
	@Size(max = 254)
	private String email;
	private String emailConfirm;

	@NotBlank
	@Range(min = 8, max= 60)
	private String password;
	private String passwordConfirm;

	private String gender;

	private String birthday;

	private String displayName;

}
