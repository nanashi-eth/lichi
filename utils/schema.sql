-- Tabla User
CREATE TABLE "user" (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    profile_picture BYTEA
);

-- Tabla Song
CREATE TABLE Song (
    song_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist VARCHAR(100) NOT NULL,
    song_year INT CHECK (song_year >= 1990 AND song_year <= EXTRACT(YEAR FROM CURRENT_DATE)),
    genre VARCHAR(50),
    duration_ms INT NOT NULL CHECK (duration_ms > 0),
    popularity INT NOT NULL CHECK (popularity >= 0 ),
    cover_image VARCHAR(255)
);

-- Tabla Playlist
CREATE TABLE Playlist (
    playlist_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user"(user_id)
);

-- Tabla Song_Playlist
CREATE TABLE Song_Playlist (
    playlist_id INTEGER NOT NULL,
    song_id INTEGER NOT NULL,
    PRIMARY KEY (playlist_id, song_id),
    FOREIGN KEY (playlist_id) REFERENCES Playlist(playlist_id) ON DELETE CASCADE,
    FOREIGN KEY (song_id) REFERENCES Song(song_id) ON DELETE CASCADE
);

-- Tabla Favorite_Song
CREATE TABLE Favorite_Song (
    user_id INTEGER NOT NULL,
    song_id INTEGER NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, song_id),
    FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE,
    FOREIGN KEY (song_id) REFERENCES Song(song_id) ON DELETE CASCADE
);

-- Índices adicionales para mejorar el rendimiento en las búsquedas
CREATE INDEX idx_user_username ON "user"(username);
CREATE INDEX idx_song_artist ON Song(artist);
CREATE INDEX idx_playlist_user_id ON Playlist(user_id);
CREATE INDEX idx_favorite_song_user_id ON Favorite_Song(user_id);
CREATE INDEX idx_favorite_song_song_id ON Favorite_Song(song_id);
CREATE INDEX idx_song_playlist_playlist_id ON Song_Playlist(playlist_id);
CREATE INDEX idx_song_playlist_song_id ON Song_Playlist(song_id);
