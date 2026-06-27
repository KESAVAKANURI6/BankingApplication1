package com.example.springboot.controller;
import com.example.springboot.dto.DepositRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }
    
    @PostMapping("/deposit")
    public String deposit(@RequestBody DepositRequest request) {
        return service.deposit(request);
    }
}