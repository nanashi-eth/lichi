package com.nanashi.lichi.services;

import com.nanashi.lichi.dto.PlaylistDTO;
import com.nanashi.lichi.model.Playlist;
import com.nanashi.lichi.model.User;
import com.nanashi.lichi.repository.PlaylistRepository;
import com.nanashi.lichi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PlaylistDTO> getAllPlaylistsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPlaylists().stream()
                .map(playlist -> PlaylistDTO.builder()
                        .playlistId(playlist.getPlaylistId())
                        .name(playlist.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Long> getSongsInPlaylist(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        return playlist.getSongs().stream()
                .map(song -> song.getSongId())
                .collect(Collectors.toList());
    }

    @Transactional
    public PlaylistDTO addOrUpdatePlaylist(Long userId, PlaylistDTO playlistDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Playlist playlist;
        if (playlistDTO.getPlaylistId() != null) {
            playlist = playlistRepository.findById(playlistDTO.getPlaylistId())
                    .orElseThrow(() -> new RuntimeException("Playlist not found"));
            playlist.setName(playlistDTO.getName());
        } else {
            playlist = Playlist.builder()
                    .name(playlistDTO.getName())
                    .user(user)
                    .build();
        }
        playlistRepository.save(playlist);
        return PlaylistDTO.builder()
                .playlistId(playlist.getPlaylistId())
                .name(playlist.getName())
                .build();
    }

    @Transactional(readOnly = true)
    public Long getUserIdByPlaylistId(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
        return playlist.getUser().getUserId();
    }
}
