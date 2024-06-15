import bcrypt

# Lista de usuarios con contraseñas en texto claro
users = [
    ('Jose', 'Benitez', 'nanashi', 'password123'),
    ('Judit', 'Bort', 'kayriu', 'password123'),
    ('Antonio', 'Guerrero', 'azen', 'password123'),
    ('Daniel', 'Rosales', 'sedani', 'password123'),
    ('Israel', 'Sanchez', 'kiwi', 'password123'),
    ('Jesus', 'Martinez', 'zarion', 'password123'),
    ('Pablo', 'Rodriguez', 'pablorodri', 'password123'),
    ('Juan Antonio', 'Garcia', 'gamunito', 'password123'),
    ('Grace', 'Taylor', 'grace_t', 'password123'),
    ('Hank', 'Anderson', 'hank_a', 'password123')
]

# Función para encriptar la contraseña
def hash_password(plain_text_password):
    # Genera una sal
    salt = bcrypt.gensalt()
    # Encripta la contraseña con la sal
    hashed_password = bcrypt.hashpw(plain_text_password.encode('utf-8'), salt)
    return hashed_password

# Generar las consultas de inserción
insert_queries = []

for first_name, last_name, username, password in users:
    hashed_password = hash_password(password)
    insert_queries.append(
        f"INSERT INTO \"user\" (first_name, last_name, username, password, profile_picture) VALUES ('{first_name}', '{last_name}', '{username}', '{hashed_password.decode('utf-8')}', NULL);"
    )

# Guardamos los inserts en un archivo
with open('inserts.sql', 'w', encoding='utf-8') as file:
    for statement in insert_queries:
        file.write(statement + '\n')
# Mostrar las consultas generadas
for query in insert_queries:
    print(query)
