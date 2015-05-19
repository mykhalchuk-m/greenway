package com.mmm.greenway.data.cfg;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.data.service.TempUserCreateService;

@Configuration
public class SecurityConfig {

	@Bean
	@Autowired
	public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {
		return new ProviderManager(Arrays.asList(daoAuthenticationProvider));
	}

	@Bean
	@Autowired
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

	@Autowired
	@Bean
	public TempUserCreateService tempUserCreateService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return new TempUserCreateService(userRepository, passwordEncoder);
	}

}
