-- Insertar playlists (cada una con un género diferente)
INSERT INTO Playlist (name, user_id) VALUES
('Rock Classics', 1),
('Pop Hits', 2),
('Jazz Essentials', 3),
('Hip Hop Vibes', 4),
('Country Roads', 5),
('Electronic Beats', 6),
('Classical Masterpieces', 7),
('Reggae Rhythms', 8),
('Blues Legends', 9),
('Indie Anthems', 10);


-- Crear función para insertar canciones en listas de reproducción
DO $$
DECLARE
    playlist_id INT;
    song_id INT;
    song_count INT;
BEGIN
    FOR playlist_id IN 1..10 LOOP
        -- Determinar cuántas canciones agregar a esta lista de reproducción (entre 20 y 50)
        song_count := 20 + (RANDOM() * 31)::INT;

        FOR i IN 1..song_count LOOP
            -- Seleccionar una canción aleatoria (entre 1 y 2000)
            song_id := 1 + (RANDOM() * 1999)::INT;

            -- Insertar la canción en la lista de reproducción
            INSERT INTO Song_Playlist (playlist_id, song_id) VALUES (playlist_id, song_id)
            ON CONFLICT DO NOTHING; -- Evitar duplicados
        END LOOP;
    END LOOP;
END $$;
