package com.ctgu.login.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.ctgu.user.model.User;

public interface LoginService {

	public User findByUsernamePw(User u);
	

	public UserDetails getUserDetails(String username);
}
