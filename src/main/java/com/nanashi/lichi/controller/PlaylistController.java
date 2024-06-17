package com.nanashi.lichi.controller;

import com.nanashi.lichi.dto.PlaylistDTO;
import com.nanashi.lichi.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PlaylistDTO>> getAllPlaylistsByUserId(@PathVariable Long userId) {
        List<PlaylistDTO> playlists = playlistService.getAllPlaylistsByUserId(userId);
        return ResponseEntity.ok(playlists);
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<List<Long>> getSongsInPlaylist(@PathVariable Long playlistId) {
        List<Long> songIds = playlistService.getSongsInPlaylist(playlistId);
        return ResponseEntity.ok(songIds);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PlaylistDTO> addOrUpdatePlaylist(@PathVariable Long userId,
                                                            @RequestBody PlaylistDTO playlistDTO) {
        PlaylistDTO updatedPlaylist = playlistService.addOrUpdatePlaylist(userId, playlistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPlaylist);
    }
}

