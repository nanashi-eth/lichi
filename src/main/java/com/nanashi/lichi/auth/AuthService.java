package com.nanashi.lichi.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nanashi.lichi.jwt.JwtService;
import com.nanashi.lichi.model.Role;
import com.nanashi.lichi.model.User;
import com.nanashi.lichi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + request.getUsername()));
            String token = jwtService.getToken(userDetails);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new UnauthorizedException("Invalid username or password");
        }

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username( request.username)
            .password(passwordEncoder.encode( request.getPassword()))
            .firstName(request.firstName)
            .lastName(request.lastName)
            .profilePicture(request.profilePicture)
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}