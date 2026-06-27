package com.example.springboot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Transaction;
import com.example.springboot.entity.User;
import com.example.springboot.repository.TransactionRepository;
import com.example.springboot.repository.UserRepository;

@Service
public class Withdrawal {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String withdraw(Long userId, Double amount) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setType("WITHDRAW");
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setUser(user);

        transactionRepository.save(transaction);

        return "Withdrawal Successful";
    }
}