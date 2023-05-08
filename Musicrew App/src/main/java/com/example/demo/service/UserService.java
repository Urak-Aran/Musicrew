package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserEntity;

public interface UserService extends UserDetailsService {
	
	String saveUser(UserDto dto);
	
	UserEntity findById(Long id);
	
	ModelAndView profile(String username);

}
