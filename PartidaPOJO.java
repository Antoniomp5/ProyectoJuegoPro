package juego;



public class PartidaPOJO {
    private Integer jugadorId;
    private Integer puntos;
    private String resultado; // ganado, perdido
 // Cracion de la base de daso en objetos para partida
    public PartidaPOJO(Integer jugadorId, Integer puntos, String resultado) {
        this.jugadorId = jugadorId;
        this.puntos = puntos;
        this.resultado = resultado;
    }

    public int getJugadorId() {
        return jugadorId;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "JugadorID: " + jugadorId + ", Puntos: " + puntos + ", Resultado: " + resultado;
    }
}
