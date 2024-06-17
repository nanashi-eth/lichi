package com.nanashi.lichi.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "artist", nullable = false, length = 100)
    private String artist;

    @Column(name = "song_year")
    private int songYear;

    @Column(name = "genre", length = 50)
    private String genre;

    @Column(name = "duration_ms", nullable = false)
    private int durationMs;

    @Column(name = "popularity", nullable = false)
    private int popularity;

    @Column(name = "cover_image", length = 255)
    private String coverImage;
}
