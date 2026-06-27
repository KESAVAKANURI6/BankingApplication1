package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.service.Withdrawal;

@RestController
@RequestMapping("/withdraw")
public class WithdrawalController {

    @Autowired
    private Withdrawal withdrawalService;

    @PostMapping
    public String withdraw(@RequestParam Long userId,
                           @RequestParam Double amount) {

        return withdrawalService.withdraw(userId, amount);
    }
}
