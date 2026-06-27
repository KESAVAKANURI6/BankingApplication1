package com.example.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dto.DepositRequest;
import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public String register(User user) {

        user.setApproved(false);
        user.setBalance(0.0);

        userRepository.save(user);

        return "Registration Request sent";
    }

    // Deposit Money
    public String deposit(DepositRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        User user = optionalUser.get();

        user.setBalance(user.getBalance() + request.getAmount());

        userRepository.save(user);

        return "Deposit Successful. Current Balance: " + user.getBalance();
    }
}