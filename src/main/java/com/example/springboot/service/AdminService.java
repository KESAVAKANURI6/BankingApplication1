package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	UserRepository userRepository;
	
	public String appriveUser(Long id) {
		User user = userRepository.findById(id).orElseThrow();
		user.setApproved(true);
		userRepository.save(user);
		return "User Approved";
	}
	

}
