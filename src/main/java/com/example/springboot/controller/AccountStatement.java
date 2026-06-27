package com.example.springboot.controller;

import java.util.List;

import com.example.springboot.entity.User;
import com.example.springboot.entity.Transaction;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountStatement {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getStatement(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        return transactionRepository.findByUser(user);
    }
}
