package juego;

public class Nave {
    private int x;  // Coordenada X de la nave
    private int y;  // Coordenada Y de la nave

    // Constructor que inicializa la posición de la nave
    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Mover la nave a la izquierda (disminuir la coordenada X)
    public void moverIzquierda() {
        x -= 10;  // Se mueve 10 unidades hacia la izquierda
    }

    // Mover la nave a la derecha (aumentar la coordenada X)
    public void moverDerecha() {
        x += 10;  // Se mueve 10 unidades hacia la derecha
    }

    // Mover la nave hacia arriba (disminuir la coordenada Y)
    public void moverArriba() {
        y -= 10;  // Se mueve 10 unidades hacia arriba
    }

    // Mover la nave hacia abajo (aumentar la coordenada Y)
    public void moverAbajo() {
        y += 10;  // Se mueve 10 unidades hacia abajo
    }

    // Obtener la posición Y de la nave
    public int getY() {
        return y;
    }

    // Obtener la posición X de la nave
    public int getX() {
        return x;
    }

    // Método para mostrar la posición actual de la nave
    public void mostrarPosicion() {
        System.out.println("Posición de la nave: X = " + x + ", Y = " + y);
    }

    public static void main(String[] args) {
        // Crear una nave en la posición (0, 0)
        Nave nave = new Nave(0, 0);
        
        // Mostrar la posición inicial
        nave.mostrarPosicion();

        // Mover la nave
        nave.moverDerecha();
        nave.moverAbajo();

        // Mostrar la nueva posición
        nave.mostrarPosicion();
    }
}
