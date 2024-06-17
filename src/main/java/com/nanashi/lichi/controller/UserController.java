package com.nanashi.lichi.controller;

import com.nanashi.lichi.model.User;
import com.nanashi.lichi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User requestedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long authenticatedUserId = getAuthenticatedUserId();
        if (!id.equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(requestedUser);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Ensure username uniqueness or handle duplicates as needed
        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null) {
            return ResponseEntity.badRequest().body(null); // Handle username already exists
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long authenticatedUserId = getAuthenticatedUserId();
        if (!id.equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt new password
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setProfilePicture(user.getProfilePicture());

        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long authenticatedUserId = getAuthenticatedUserId();
        if (!id.equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    private Long getAuthenticatedUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User authenticatedUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        return authenticatedUser.getUserId();
    }
}
