package juego;


import java.util.*;

public class BDPojoManager {
    private Map<Integer, JugadorPOJO> jugadores = new HashMap<>();
    private List<PartidaPOJO> partidas = new ArrayList<>();
    private int ultimoId = 1;

    public int buscarJugador(String nombre) {
        for (JugadorPOJO j : jugadores.values()) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                return j.getId();
            }
        }
        return -1;
    }
    // Llamada a las base de datos creadas
    public int crearJugador(String nombre) {
        int nuevoId = ultimoId++;
        jugadores.put(nuevoId, new JugadorPOJO(nuevoId, nombre));
        return nuevoId;
    }

    public void actualizarJugador(int id, int puntos, int vidas, String estado) {
        JugadorPOJO j = jugadores.get(id);
        if (j != null) {
            j.setPuntos(puntos);
            j.setVidas(vidas);
            j.setEstado(estado);
        }
    }

    public void guardarPartida(int jugadorId, int puntos, String resultado) {
        partidas.add(new PartidaPOJO(jugadorId, puntos, resultado));
    }

    public JugadorPOJO getJugador(int id) {
        return jugadores.get(id);
    }

    public List<PartidaPOJO> getPartidas() {
        return partidas;
    }

    public void mostrarJugadores() {
        for (JugadorPOJO j : jugadores.values()) {
            System.out.println(j);
        }
    }


	
}