package com.example.springboot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Transaction;
import com.example.springboot.entity.User;
import com.example.springboot.repository.TransactionRepository;
import com.example.springboot.repository.UserRepository;

@Service
public class Deposit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public User deposit(Long userId, Double amount) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBalance(user.getBalance() + amount);

        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setUser(user);

        transactionRepository.save(transaction);

        return user;
    }
}