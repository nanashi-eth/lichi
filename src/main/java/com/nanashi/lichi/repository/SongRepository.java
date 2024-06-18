package com.nanashi.lichi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nanashi.lichi.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query("SELECT s FROM Song s WHERE lower(s.title) LIKE lower(concat('%', :keyword, '%')) " +
           "OR lower(s.artist) LIKE lower(concat('%', :keyword, '%')) " +
           "OR lower(s.genre) LIKE lower(concat('%', :keyword, '%')) " )
    Page<Song> searchByKeyword(String keyword, Pageable pageable);
}

