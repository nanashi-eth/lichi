
import pandas as pd
from unidecode import unidecode
import re


# Cargamos el CSV
df = pd.read_csv('./songs.csv')

# Función para formatear el nombre de archivo
def format_filename(filename):
    # Eliminar acentos y caracteres especiales
    filename = unidecode(filename)
    # Reemplazar caracteres no alfanuméricos por guiones bajos
    filename = re.sub(r'[^\w\s]', '', filename)
    # Reemplazar espacios por guiones bajos
    filename = filename.replace(' ', '_')
    return filename + '.jpg'

# # Definimos una función para clasificar el género
# def classify_genre(row):
#     energy = row['energy']
#     danceability = row['danceability']
#     tempo = row['tempo']
#     speechiness = row['speechiness']
#     valence = row['valence']
#     acousticness = row['acousticness']
#     instrumentalness = row['instrumentalness']
#     key = row['key']
#     mode = row['mode']

#     if energy > 0.6 and tempo > 90 and valence > 0.3:
#         return "Pop"
#     elif energy > 0.4 and tempo > 70 and speechiness > 0.1:
#         return "Rap"
#     elif acousticness > 0.4:
#         return "Acoustic"
#     elif instrumentalness > 0.3:
#         return "Instrumental"
#     elif key in [0, 2, 4, 5, 7, 9, 11] and mode == 1:
#         return "Rock"
#     elif key in [1, 3, 6, 8, 10] and mode == 0:
#         return "Blues"
#     elif danceability > 0.6 and valence > 0.4:
#         return "Dance"
#     elif energy > 0.5 and tempo > 100 and valence > 0.4:
#         return "Party"
#     else:
#         return "Indefinido"

# # Aplicamos la función a cada fila del DataFrame para clasificar el género
# df['genre'] = df.apply(classify_genre, axis=1)

# # Agregamos el campo 'popularity' con un valor numérico incremental
# df['popularity'] = range(1, len(df) + 1)

# # Agregar campo de nombre de archivo de imagen
# df['image_file'] = df['name'].apply(format_filename)

# Generamos los inserts para la tabla 'songs' incluyendo 'duration_ms' y 'image_filename'
insert_statements = []
for index, row in df.iterrows():
    name = row['song'].replace("'", "''")
    artist = row['artist'].replace("'", "''")
    genre = row['genre'].replace("'", "''")
    filename = format_filename(row['song'])
    insert_statement = f"INSERT INTO song (title, artist, genre, popularity, duration_ms, song_year, cover_image) VALUES ('{name}', '{artist}', '{genre}', {row['popularity']}, {row['duration_ms']}, {row['year']}, '{filename}');"
    insert_statements.append(insert_statement)
    
# Muestra de los géneros asignados a cada canción
print(df[['song', 'artist', 'genre']])

# Guardamos los inserts en un archivo
with open('songs.sql', 'w', encoding='utf-8') as file:
    for statement in insert_statements:
        file.write(statement + '\n')

print("Inserts generados y guardados en 'inserts.sql'")

