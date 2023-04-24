package com.example.demo.security;

import com.example.demo.constant.Roles;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


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

        String page = "";

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (Roles.SUPER_ADMIN.equals(auth.getAuthority())) {
                // TODO Define Page for Super Admin
                page = "/profile";
            }else if (Roles.ADMIN.equals(auth.getAuthority())) {
                // TODO Define Page for Admin
                page = "/profile";
            }else if (Roles.USER.equals(auth.getAuthority())) {
                // TODO Define Page for User
                page = "/profile";
            }
        }

        String userName = ((User)authentication.getPrincipal()).getUsername();
        UserEntity user = userRepository.findByUsername(userName);
        session.setAttribute("USER_ID", user.getUsername());

        httpServletResponse.sendRedirect(page);
    }
}
