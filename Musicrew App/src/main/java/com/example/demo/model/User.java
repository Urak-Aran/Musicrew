package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
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
	@Size(min = 6, max= 60)
	private String password;
	
	private String gender;

	private String birthday;

	private String displayName;


}
