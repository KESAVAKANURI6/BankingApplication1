package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.service.FundTransfer;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private FundTransfer fundTransfer;

    @PostMapping
    public String transfer(@RequestParam Long senderId,
                           @RequestParam Long receiverId,
                           @RequestParam Double amount) {

        return fundTransfer.transfer(senderId, receiverId, amount);
    }
}
