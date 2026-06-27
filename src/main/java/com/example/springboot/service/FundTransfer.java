package com.example.springboot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Transaction;
import com.example.springboot.entity.User;
import com.example.springboot.repository.TransactionRepository;
import com.example.springboot.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class FundTransfer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public String transfer(Long senderId,
                           Long receiverId,
                           Double amount) {

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender Not Found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver Not Found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        // Sender Transaction
        Transaction senderTransaction = new Transaction();
        senderTransaction.setType("TRANSFER_SENT");
        senderTransaction.setAmount(amount);
        senderTransaction.setDate(LocalDateTime.now());
        senderTransaction.setUser(sender);

        transactionRepository.save(senderTransaction);

        // Receiver Transaction
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setType("TRANSFER_RECEIVED");
        receiverTransaction.setAmount(amount);
        receiverTransaction.setDate(LocalDateTime.now());
        receiverTransaction.setUser(receiver);

        transactionRepository.save(receiverTransaction);

        return "Transfer Successful";
    }
}