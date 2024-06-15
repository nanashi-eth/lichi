package com.nanashi.lichi.services;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import com.nanashi.lichi.repository.UserRepository;
import com.nanashi.lichi.dto.UserDTO;
import com.nanashi.lichi.model.*;
import com.nanashi.lichi.request.UserRequest;
import com.nanashi.lichi.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository; 

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
       
        User user = User.builder()
        .userId(userRequest.getUserId())
        .firstName(userRequest.getFirstName())
        .lastName(userRequest.getLastName())
        .profilePicture(userRequest.getProfilePicture())
        .role(Role.USER)
        .build();
        
        userRepository.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getProfilePicture());

        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO getUser(long id) {
        User user= userRepository.findById(id).orElse(null);
       
        if (user!=null)
        {
            UserDTO userDTO = UserDTO.builder()
            .userId(user.getId())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .profilePicture(user.getProfilePicture())
            .build();
            return userDTO;
        }
        return null;
    }
}
