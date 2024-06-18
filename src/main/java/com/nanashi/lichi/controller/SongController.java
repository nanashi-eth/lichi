package com.nanashi.lichi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanashi.lichi.model.Song;
import com.nanashi.lichi.services.SongService;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin(origins = { "http://localhost:4200" })
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<Song> getAllSongs(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        return songService.getAllSongs(PageRequest.of(page, size));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public Page<Song> searchSongs(@RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        return songService.searchSongs(keyword, PageRequest.of(page, size));
    }
}
