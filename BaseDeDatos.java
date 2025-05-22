package Juego;

import java.util.HashMap;

public class BaseDeDatos {
    private static HashMap<String, jugador> jugadores = new HashMap<>();

    public static void guardarEstadisticas(jugador jugador) {
        jugadores.put(jugador.getNombre(), jugador);
    }

    public static jugador obtenerEstadisticas(String nombre) {
        return jugadores.get(nombre);
    }

    public static void mostrarEstadisticas() {
        for (jugador j : jugadores.values()) {
            System.out.println(j);
        }
    }
}
