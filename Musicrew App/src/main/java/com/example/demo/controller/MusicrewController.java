package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.User;

@Controller
public class MusicrewController {

	@GetMapping("/")
	public String login() {
		return "login";
	}

	@GetMapping("/memberRegistration")
	public String memberRegistration(@ModelAttribute User user) {
		return "memberRegistration";
	}

	@PostMapping("/confirmingRegistration")
	public String confirmingRegistration(@Validated @ModelAttribute User user, BindingResult result) {
		if (result.hasErrors()) {
			return "memberRegistration";
		}
		return "confirmingRegistration";
	}

	@GetMapping("/top")
	public String top() {
		return "top";
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}

	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}

	@GetMapping("/messages")
	public String messages() {
		return "messages";
	}

	@GetMapping("/settings")
	public String settings() {
		return "settings";
	}

}
