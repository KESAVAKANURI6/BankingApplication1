package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.entity.User;
import com.example.springboot.service.Deposit;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    @Autowired
    private Deposit depositService;

    @PostMapping
    public User deposit(@RequestParam Long userId,
                        @RequestParam Double amount) {

        return depositService.deposit(userId, amount);
    }
}
