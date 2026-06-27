package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.entity.Transaction;
import com.example.springboot.entity.User;
import com.example.springboot.repository.TransactionRepository;
import com.example.springboot.repository.UserRepository;

@RestController
@RequestMapping("/statement")
public class StatementController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<Transaction> getStatement(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return transactionRepository.findByUser(user);
    }
}