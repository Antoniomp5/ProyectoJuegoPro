package juego;

public class Nave {
    private int x;
    private int y;
    private static final int MIN_POS = 0;
    private static final int MAX_POS = 9; // Asumiendo un área de juego de 10x10

    public Nave() {
        this.x = 5; // Posición inicial en el centro
        this.y = 5;
    }

    public void moverIzquierda() throws NaveException {
        if (x <= MIN_POS) {
            throw new NaveException("La nave no puede moverse más a la izquierda.");
        }
        x--;
    }

    public void moverDerecha() throws NaveException {
        if (x >= MAX_POS) {
            throw new NaveException("La nave no puede moverse más a la derecha.");
        }
        x++;
    }

    public void moverArriba() throws NaveException {
        if (y <= MIN_POS) {
            throw new NaveException("La nave no puede moverse más arriba.");
        }
        y--;
    }

    public void moverAbajo() throws NaveException {
        if (y >= MAX_POS) {
            throw new NaveException("La nave no puede moverse más abajo.");
        }
        y++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Nave: (" + x + ", " + y + ")";
    }
}

