package com.wissen.ganesh.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	@Value("${ROOT_USER:rootuser}")
	public String envUser;

	@Value("${ROOT_PASSWORD:rootpassword}")
	public String envPassword;

	@Autowired
	public PasswordEncoder pass;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (inMemoryUserList.isEmpty()) {
			 addUserDetails();
		}

		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

	private void addUserDetails() {

		inMemoryUserList.add(new JwtUserDetails(1L, envUser, pass.encode(envPassword), "ROLE_USER_2"));
	}
}
