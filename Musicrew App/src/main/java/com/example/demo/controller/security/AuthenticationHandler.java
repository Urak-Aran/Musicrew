package com.example.demo.controller.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {

	@Autowired
	HttpSession session;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException {

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if ("SUPER_ADMIN".equals(auth.getAuthority())) {
				httpServletResponse.sendRedirect("/home");
			} else if ("ADMIN".equals(auth.getAuthority())) {
				httpServletResponse.sendRedirect("/home");
			} else if ("USER".equals(auth.getAuthority())) {
				httpServletResponse.sendRedirect("/home");
			}
		}

		String userName = ((User) authentication.getPrincipal()).getUsername();
		com.example.demo.model.User user = userRepository.findByUsername(userName);
		session.setAttribute("USER_ID", user.getUsername());
	}
}