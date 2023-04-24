package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserEntity;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	

	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping("/memberRegistration")
	public String memberRegistration(Model model) {
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//System.out.print("PAsSWOD IS " + passwordEncoder.encode("admin"));
		
		
		model.addAttribute("user", new UserDto());

		return "memberRegistration";
	}
	
	
	@PostMapping("/memberRegistration/save")
	public String saveMember(@ModelAttribute UserDto userDto) {
		
		
		return userService.saveUser(userDto);
		
	}
	

	@PostMapping("/profile")
	public String confirmingRegistration(@Validated @ModelAttribute UserEntity userEntity, BindingResult result, Model model) {
		

		if (result.hasErrors()) {
			return "memberRegistration";
		}

		return "login";
	}
}
