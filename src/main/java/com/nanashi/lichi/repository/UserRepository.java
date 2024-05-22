package com.nanashi.lichi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanashi.lichi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}