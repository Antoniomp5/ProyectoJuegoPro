create database juego;

CREATE TABLE jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    puntos INT DEFAULT 0,
    vidas INT DEFAULT 3,
    estado ENUM('jugando', 'ganado', 'perdido') DEFAULT 'jugando',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jugador_id INT,
    puntos_final INT,
    resultado ENUM('ganado', 'perdido'),
    fecha_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id)
);
 
