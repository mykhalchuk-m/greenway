package com.mmm.greenway.data.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmm.greenway.data.repository.UserRepository;
import com.mmm.greenway.entity.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOne(username);
		if (user == null) {
			return null;
		} else {
			return new UserDetails() {
				private static final long serialVersionUID = -4550627259324221582L;

				@Override
				public boolean isEnabled() {
					return user.getEnabled();
				}

				@Override
				public boolean isCredentialsNonExpired() {
					return user.getCredentialsNonExpired();
				}

				@Override
				public boolean isAccountNonLocked() {
					return user.getAccountNonLocked();
				}

				@Override
				public boolean isAccountNonExpired() {
					return user.getAccountNonExpired();
				}

				@Override
				public String getUsername() {
					return user.getUserName();
				}

				@Override
				public String getPassword() {
					return user.getPassword();
				}

				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					return user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.toString()))
							.collect(Collectors.toList());
				}
			};
		}
	}
}
