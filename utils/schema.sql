-- Tabla User
CREATE TABLE "user" (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    profile_picture BYTEA
);
-- Tabla Song
CREATE TABLE Song (
    song_id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    artist VARCHAR(100) NOT NULL,
    album VARCHAR(100),
    genre VARCHAR(50),
    duration_ms INT NOT NULL,
    popularity INT NOT NULL,
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
    FOREIGN KEY (playlist_id) REFERENCES Playlist(playlist_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);
-- Tabla Favorite_Song
CREATE TABLE Favorite_Song (
    user_id INTEGER NOT NULL,
    song_id INTEGER NOT NULL,
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, song_id),
    FOREIGN KEY (user_id) REFERENCES "user"(user_id),
    FOREIGN KEY (song_id) REFERENCES Song(song_id)
);