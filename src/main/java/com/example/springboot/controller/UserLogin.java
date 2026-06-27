package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;

public class UserLogin {
	
	public String login(String username, String password) {
		
		User user= UserRepository.findByUsername(username).orElseThrow();
		if(!user.isApproved())
			return "Account Not Approved";
		if(user.getPassword().equals(password))
			return "Login Sucess";
		return "Invaild Credentials";
	}

}
