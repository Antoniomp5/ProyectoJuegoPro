package juego;

public class Jugador {
    private String nombre;    // Nombre del jugador
    private int vidas;        // Número de vidas del jugador
    private int puntos;       // Puntos obtenidos por el jugador

    // Constructor que inicializa el nombre del jugador, y asigna 3 vidas y 0 puntos por defecto
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vidas = 3;      // El jugador comienza con 3 vidas
        this.puntos = 0;     // El jugador comienza con 0 puntos
    }

    // Método para restar una vida cuando el jugador pierde una
    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    // Método para sumar puntos al jugador
    public void ganarPuntos(int puntos) {
        this.puntos += puntos;
    }

    // Método que verifica si el jugador ha ganado
    public boolean haGanado() {
        // Supongamos que el jugador gana cuando alcanza 100 puntos
        return puntos >= 100;
    }

    // Método que devuelve el nombre del jugador
    public String getNombre() {
        return nombre;
    }

    // Método que verifica si el jugador ha perdido todas sus vidas
    public boolean haPerdido() {
        return vidas <= 0;
    }

    // Métodos para obtener la cantidad de puntos y vidas (si es necesario)
    public int getPuntos() {
        return puntos;
    }

    public int getVidas() {
        return vidas;
    }

    // Método para mostrar la información del jugador
    public void mostrarInformacion() {
        System.out.println("Jugador: " + nombre);
        System.out.println("Puntos: " + puntos);
        System.out.println("Vidas: " + vidas);
    }

    // Método principal para probar la clase Jugador
    public static void main(String[] args) {
        // Crear un jugador llamado "Carlos"
        Jugador jugador = new Jugador("Carlos");

        // Mostrar información inicial del jugador
        jugador.mostrarInformacion();

        // El jugador gana puntos
        jugador.ganarPuntos(50);
        jugador.mostrarInformacion();

        // El jugador pierde una vida
        jugador.perderVida();
        jugador.mostrarInformacion();

        // El jugador gana más puntos
        jugador.ganarPuntos(60);
        jugador.mostrarInformacion();

        // Verificar si el jugador ha ganado
        if (jugador.haGanado()) {
            System.out.println(jugador.getNombre() + " ha ganado el juego.");
        } else {
            System.out.println(jugador.getNombre() + " aún no ha ganado.");
        }

        // Verificar si el jugador ha perdido
        if (jugador.haPerdido()) {
            System.out.println(jugador.getNombre() + " ha perdido todas sus vidas.");
        }
    }
}
