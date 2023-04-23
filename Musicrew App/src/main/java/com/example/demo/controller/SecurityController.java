package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.LoginDto;

@Controller
public class SecurityController {

	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new LoginDto());
		return "login";
	}
}