package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MusicrewPageController {
	
	private final UserService userService;
	
	

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

	public MusicrewPageController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}

	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/profile")
//	@PreAuthorize("hasRole('Admin')")
	public ModelAndView profile(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("USER_ID")!= null) {
			
			String userName = (String)request.getSession().getAttribute("USER_ID");
			
			return userService.profile(userName);
			
		}
	
		return null;
		
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
