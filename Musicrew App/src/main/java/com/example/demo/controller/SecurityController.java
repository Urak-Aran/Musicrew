package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.LoginDto;

@Controller
public class SecurityController {

	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new LoginDto());
		return "login";
	}

	@PostMapping("/login")
	public String showList(Authentication loginUser, Model model) {

		model.addAttribute("username", loginUser.getName());
		model.addAttribute("authority", loginUser.getAuthorities());
		return "top";
		
	}
}