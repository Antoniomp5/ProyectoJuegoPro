package juego;

import java.util.Random;

public class Meteoro {
    private int x;  // Coordenada X del meteoro
    private int y;  // Coordenada Y del meteoro

    // Constructor que inicializa la posición del meteoro
    public Meteoro(int maxWidth, int maxHeight) {
        Random rand = new Random();
        // Inicializa la posición del meteoro en una coordenada aleatoria
        // La coordenada X está entre 0 y maxWidth
        this.x = rand.nextInt(maxWidth);
        // La coordenada Y está en la parte superior de la pantalla (por ejemplo, Y = 0)
        this.y = 0;  // El meteoro empieza en la parte superior (puedes ajustar este valor)
    }

    // Obtener la posición X del meteoro
    public int getX() {
        return x;
    }

    // Obtener la posición Y del meteoro
    public int getY() {
        return y;
    }

    // Método para mover el meteoro (por ejemplo, que se mueva hacia abajo)
    public void mover() {
        y += 10;  // El meteoro baja 10 unidades en cada actualización
    }

    // Método para mostrar la posición del meteoro
    public void mostrarPosicion() {
        System.out.println("Posición del meteoro: X = " + x + ", Y = " + y);
    }

    // Método principal para probar el meteoro
    public static void main(String[] args) {
        // Crear un meteoro en una pantalla de 800x600 (por ejemplo)
        Meteoro meteoro = new Meteoro(800, 600);

        // Mostrar la posición inicial
        meteoro.mostrarPosicion();

        // Mover el meteoro
        meteoro.mover();

        // Mostrar la nueva posición
        meteoro.mostrarPosicion();
    }
}
