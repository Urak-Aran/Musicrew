package com.example.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

	private final UserRepository userRepository;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				// 「cssやjs、imagesなどの静的リソース」をアクセス可能にします
				.requestMatchers("/css/**", "/memberRegistration", "/js/**", "/images/**", "/webjars/**", "/favicon.*",
						"/*/icon-*")
				.permitAll()
				.anyRequest().authenticated())
				.formLogin((login) -> login
						// ログイン時のURLを指定
						.loginPage("/login").failureHandler(

								new AuthenticationFailureHandler() {

									@Override
									public void onAuthenticationFailure(HttpServletRequest request,
											HttpServletResponse response, AuthenticationException exception)
											throws IOException, ServletException {
										// TODO 自動生成されたメソッド・スタブ
										String error = exception.getMessage();
										System.out.println("A failed login reason: " + error);

									}
								}

						)
						// 認証後にリダイレクトする場所を指定
						.defaultSuccessUrl("/top")
						.permitAll())
				// ログアウトの設定
				.logout(logout -> logout
						// ログアウト時のURLを指定
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.permitAll())
				.rememberMe();
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			// ユーザ名を検索します（ユーザが存在しない場合は、例外をスローします）
			var user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

			// ユーザ情報を返します
			return new User(user.getUsername(), user.getPass(),
					AuthorityUtils.createAuthorityList("ADMIN"));
		};
	}
}
