package com.account.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.account.model.Account;
import com.account.repo.AccountRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService  {
	@Autowired
	private AccountRepo accountRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account=accountRepo.findByUsername(username);
		return new User(account.getUsername(), account.getPassword(), Arrays.asList());
	}
}
