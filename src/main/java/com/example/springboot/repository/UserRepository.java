package com.example.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    static Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

    List<User> findByApprovedFalse();
}