package com.nanashi.lichi.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nanashi.lichi.model.Song;
import com.nanashi.lichi.repository.SongRepository;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Page<Song> getAllSongs(PageRequest pageable) {
        return songRepository.findAll(pageable);
    }
}

