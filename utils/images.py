import pandas as pd
import re
import os
from unidecode import unidecode
import random
import time
from bing_image_downloader import downloader

# Función para formatear el nombre de archivo
def format_filename(filename):
    # Eliminar acentos y caracteres especiales
    filename = unidecode(filename)
    # Reemplazar caracteres no alfanuméricos por guiones bajos
    filename = re.sub(r'[^\w\s]', '', filename)
    # Reemplazar espacios por guiones bajos
    filename = filename.replace(' ', '_')
    return filename

# Crear el directorio para guardar las imágenes si no existe
output_dir = "./covers"
os.makedirs(output_dir, exist_ok=True)

# Leer el CSV con pandas
df = pd.read_csv('./songs.csv')

# Descargar imágenes para cada canción
for index, row in df.iterrows():
    title = row['song']
    artist = row['artist']
    
    # Modificar la consulta para buscar imágenes con relación de aspecto 1:1 y que sean archivos JPG
    query = f"{title} {artist} album cover"
    
    try:
        print(f"Buscando imágenes para: {query}")
        downloader.download(format_filename(title), query, limit=1, output_dir=output_dir, adult_filter_off=True, force_replace=False, timeout=60, verbose=True)
        print(f"Imágenes descargadas para: {query}")
        
    except Exception as e:
        print(f"Error al descargar imágenes para: {query}. Error: {e}")

    # Esperar un tiempo aleatorio antes de realizar la siguiente solicitud para evitar bloqueos
    time.sleep(random.uniform(1, 3))

print("Descarga de imágenes completada.")
