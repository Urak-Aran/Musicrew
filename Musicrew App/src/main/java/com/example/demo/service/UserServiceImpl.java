package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	private final RoleRepository roleRepository;
	
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository,
			RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder =passwordEncoder;
		this.roleRepository =roleRepository;
	}

	@Override
	public String saveUser(UserDto dto) {

		UserEntity entity = dto.toEntity();
		
		entity.setPass(passwordEncoder.encode(entity.getPass()));
		entity.setRole(roleRepository.findById(2L).get());
		
		userRepository.save(entity);
		
		return "memberRegistration";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (username != null) {
			UserEntity userEntity = userRepository.findByUsername(username);

			if (userEntity == null) {

				throw new UsernameNotFoundException(username);
			}
			return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
					userEntity.getPass(), getUserAuthority(userEntity));

		}
		return null;
	}

	private List<GrantedAuthority> getUserAuthority(UserEntity userEntity) {
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(userEntity.getRole().getRole()));
		return new ArrayList<>(roles);
	}

}
