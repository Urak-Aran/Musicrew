package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;

@Controller
public class MusicrewPageController {

	/*@GetMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/login")
	public String redirectLogin() {
		return "login";
	}*/
	
	/*@GetMapping("/memberRegistration")
	public String memberRegistration(@ModelAttribute User user) {
		return "memberRegistration";
	}
	
	@PostMapping("/confirmingRegistration")
	public String confirmingRegistration(@Validated @ModelAttribute User user, BindingResult result) {
		if (!user.getEmail().equals(user.getEmailConfirm())) {
			return "memberRegistration";
		}
		if (result.hasErrors()) {
			return "memberRegistration";
		}
		return "confirmingRegistration";
	}*/

	@GetMapping("/top")
	public String top() {
		return "top";
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}

	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/profile")
//	@PreAuthorize("hasRole('Admin')")
	public String profile(@PathVariable String username, Model model) {
	UserEntity user = userRepository.findByUsername(username);
	model.addAttribute("user", user);
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
