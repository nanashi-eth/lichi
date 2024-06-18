Lichi music api
# Sistema de Gestión de Música API

Este proyecto es una API RESTful desarrollada con Spring Boot que proporciona operaciones CRUD para la gestión de música y usuarios. Incluye autenticación JWT, manejo de CORS, sistema globalizado de manejo de excepciones, logging personalizado y despliegue con Docker y Docker Compose.

## Características

- **CRUD de Usuarios**: Crear, Leer, Actualizar y Eliminar usuarios.
- **CRUD de Música**: Crear, Leer, Actualizar y Eliminar playlists.
- **Autenticación JWT**: Seguridad implementada mediante JSON Web Tokens.
- **CORS**: Configuración para permitir solicitudes de diferentes orígenes.
- **Manejo Global de Excepciones**: Gestión centralizada de errores.
- **Logging Personalizado**: Sistema de logging configurado para registrar eventos y errores.
- **Despliegue con Docker**: Dockerfile y Docker Compose configurados para despliegue fácil y rápido.

## Requisitos Previos

- Docker y Docker Compose instalados en tu máquina.
- JDK 17 .
- Maven 3.9.3 .

## Configuración del Proyecto

### Configuración de Spring Boot

#### Archivo `application.properties`

```properties
# Puerto del servidor
server.port=8080

# Configuración de la base de datos (valores en las variables de entorno del proyecto)
spring.datasource.url=jdbc:postgresql://db:5432/${DBNAME}
spring.datasource.username=${DBUSERNAME}
spring.datasource.password=${DBPASSWORD}

# Configuración JWT
jwt.secret=${SECRETKEY}
jwt.expirationMs=${EXP}

# Configuración CORS
cors.allowed-origins=http://localhost:4200

# Configuración de logging
logging.level.root=INFO
logging.level.com.nanashi.lichi=DEBUG
logging.file.name=logs/lichi.log
```

## Endpoints Principales

### Autenticación

- `POST /auth/login`: Autentica un usuario y retorna un JWT.
- `POST /auth/register`: Crea un nuevo usuario y retorna un JWT.

### Usuarios

- `GET api/users`: Obtiene todos los usuarios.
- `PUT /users/{id}`: Actualiza un usuario existente.
- `DELETE /users/{id}`: Elimina un usuario.

### Música

- `GET /api/songs`: Obtiene todas las canciones (si has inciado sesion).
- `GET /api/playlists/user/{userId}`: Obtiene todas las playlists del usuario registrado.
- `GET /api/playlists/{playlistId}/songs`: Obtiene todas las canciones de la playlist.
- `POST /api/playlists/user/{userId}`: Crea una nueva playlist.
- `GET api/songs/search`: Obtiene todas las canciones que cumplen el filtro que se le pasa como parametro.
- `DELETE /api/playlists/{playlistId}`: Elimina una playlist.

BD Schema
![ERDiagram](https://github.com/nanashi-eth/lichi/assets/148278933/76725e40-a39b-41e6-93b0-2c1c1586c1d7)

## Documentación

- [Documentación de Spring Boot](https://spring.io/projects/spring-boot)
- [Documentación de Spring Security](https://spring.io/projects/spring-security)
- [Documentación de JWT](https://jwt.io/introduction/)
- [Documentación de PostgreSQL](https://www.postgresql.org/docs/)
- [Documentación de Docker](https://docs.docker.com/)
- [Documentación de Docker Compose](https://docs.docker.com/compose/)

