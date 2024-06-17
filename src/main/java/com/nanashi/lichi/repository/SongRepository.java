package com.nanashi.lichi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nanashi.lichi.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
}

