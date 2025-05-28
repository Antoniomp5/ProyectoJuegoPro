package juego;

import java.sql.*;

public class BDManager {
    private static final String URL = "jdbc:mysql://localhost:3306/space_invaders";
    private static final String USER = "tu_usuario";
    private static final String PASS = "tu_password";

    private Connection conn;

    public BDManager() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    // Buscar jugador por nombre, devuelve ID o -1 si no existe
    public int buscarJugador(String nombre) throws SQLException {
        String sql = "SELECT id FROM jugadores WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return -1;
                }
            }
        }
    }

    // Crear nuevo jugador, devuelve el ID generado
    public int crearJugador(String nombre) throws SQLException {
        String sql = "INSERT INTO jugadores (nombre) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, nombre);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    // Actualizar puntos, vidas y estado del jugador
    public void actualizarJugador(int id, int puntos, int vidas, String estado) throws SQLException {
        String sql = "UPDATE jugadores SET puntos = ?, vidas = ?, estado = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, puntos);
            ps.setInt(2, vidas);
            ps.setString(3, estado);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    // Guardar partida al finalizar
    public void guardarPartida(int jugadorId, int puntosFinales, String resultado) throws SQLException {
        String sql = "INSERT INTO partidas (jugador_id, puntos_final, resultado) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jugadorId);
            ps.setInt(2, puntosFinales);
            ps.setString(3, resultado);
            ps.executeUpdate();
        }
    }
}
