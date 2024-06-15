package com.nanashi.lichi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    long userId;
    String username;
    String firstName;
    String lastName;
    byte [] profilePicture;
}
