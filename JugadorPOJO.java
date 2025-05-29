package juego;

public class JugadorPOJO {
    private Integer id;
    private String nombre;
    private Integer puntos;
    private Integer vidas;
    private String estado; // jugando, ganado, perdido
    // Cracion de la base de daso en objetos para jugador.
    public JugadorPOJO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.puntos = 0;
        this.vidas = 3;
        this.estado = "jugando";
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre + " - Puntos: " + puntos + " - Vidas: " + vidas + " - Estado: " + estado;
    }
}

