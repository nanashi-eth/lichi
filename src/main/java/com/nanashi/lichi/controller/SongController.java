package com.nanashi.lichi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nanashi.lichi.model.Song;
import com.nanashi.lichi.services.SongService;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }
}
