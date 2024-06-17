package com.nanashi.lichi.controller;

import com.nanashi.lichi.dto.PlaylistDTO;
import com.nanashi.lichi.jwt.JwtService;
import com.nanashi.lichi.model.User;
import com.nanashi.lichi.repository.UserRepository;
import com.nanashi.lichi.services.PlaylistService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylistsByUserId(@PathVariable Long userId, HttpServletRequest request) {
        Long authenticatedUserId = getUserIdFromTokenInHeader(request);
        
        if (!userId.equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<PlaylistDTO> playlists = playlistService.getAllPlaylistsByUserId(userId);
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<List<Long>> getSongsInPlaylist(@PathVariable Long playlistId, HttpServletRequest request) {
        Long authenticatedUserId = getUserIdFromTokenInHeader(request);
        Long ownerUserId = playlistService.getUserIdByPlaylistId(playlistId);

        if (!authenticatedUserId.equals(ownerUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Long> songIds = playlistService.getSongsInPlaylist(playlistId);
        return ResponseEntity.ok(songIds);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PlaylistDTO> addOrUpdatePlaylist(@PathVariable Long userId,
                                                            @RequestBody PlaylistDTO playlistDTO,
                                                            HttpServletRequest request) {
        Long authenticatedUserId = getUserIdFromTokenInHeader(request);

        if (!userId.equals(authenticatedUserId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        PlaylistDTO updatedPlaylist = playlistService.addOrUpdatePlaylist(userId, playlistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPlaylist);
    }

    private Long getUserIdFromTokenInHeader(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
            return getUserIdFromToken(token);
        }
        return null;
    }

    private Long getUserIdFromToken(String token) {
        String username = jwtService.getUsernameFromToken(token);
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(User::getUserId).orElse(null);
    }
}


