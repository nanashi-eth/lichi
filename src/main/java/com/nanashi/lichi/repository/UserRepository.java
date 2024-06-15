package com.nanashi.lichi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.nanashi.lichi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); 
    
    @Transactional
    @Modifying
    @Query("update User u set u.firstName = :firstname, u.lastName = :lastname, u.profilePicture = :profilepicture where u.userId = :id")
    void updateUser(@Param("id") Long id, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("profilepicture") byte[] profilepicture);
}
