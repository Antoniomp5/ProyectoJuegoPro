package juego;

import java.util.ArrayList;

public class Juego {
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        BDManager bd = null;
        try {
            System.out.println("¡Bienvenido al Space Invaders!");
            System.out.println("Introduce tu nombre: ");
            String nombre = sc.nextLine();

            bd = new BDManager();

            int jugadorId = bd.buscarJugador(nombre);
            if (jugadorId == -1) {
                jugadorId = bd.crearJugador(nombre);
                System.out.println("Nuevo jugador creado.");
            } else {
                System.out.println("Jugador encontrado.");
            }

            Jugador jugador = new Jugador(nombre);

            Nave nave = new Nave();
            ArrayList<Meteoro> meteoros = new ArrayList<>();

            while (!jugador.haGanado() && !jugador.haPerdido()) {
                if (rand.nextInt(10) < 3) {
                    meteoros.add(new Meteoro(rand.nextInt(10), 0));
                }

                for (Meteoro meteoro : new ArrayList<>(meteoros)) {
                    meteoro.mover();
                    if (meteoro.getX() == nave.getX() && meteoro.getY() == nave.getY()) {
                        jugador.perderVida();
                        meteoros.remove(meteoro);
                        System.out.println("¡Impacto! Has perdido una vida.");
                    }
                }

                System.out.println(jugador);
                System.out.println(nave);

                System.out.println("Elige una opción:");
                System.out.println("1. Mover izquierda");
                System.out.println("2. Mover derecha");
                System.out.println("3. Mover arriba");
                System.out.println("4. Mover abajo");
                System.out.println("5. Disparar");

                int opcion = Integer.parseInt(sc.nextLine());
                try {
                    switch (opcion) {
                        case 1: nave.moverIzquierda(); break;
                        case 2: nave.moverDerecha(); break;
                        case 3: nave.moverArriba(); break;
                        case 4: nave.moverAbajo(); break;
                        case 5:
                            jugador.ganarPuntos(100);
                            System.out.println("¡Disparo realizado!");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } catch (NaveException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                // Actualizar estado en BD cada ciclo
                bd.actualizarJugador(jugadorId, jugador.getPuntos(), jugador.getVidas(),
                        jugador.haGanado() ? "ganado" : jugador.haPerdido() ? "perdido" : "jugando");
            }

            if (jugador.haGanado()) {
                System.out.println("¡Felicidades, " + jugador.getNombre() + "! Has ganado.");
                bd.guardarPartida(jugadorId, jugador.getPuntos(), "ganado");
            } else {
                System.out.println("¡Lo siento, " + jugador.getNombre() + "! Has perdido.");
                bd.guardarPartida(jugadorId, jugador.getPuntos(), "perdido");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, introduce un número.");
        } catch (ConcurrentModificationException e) {
            System.out.println("Error al modificar la lista de meteoros.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (bd != null) {
                try {
                    bd.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            sc.close();
        }
    }
}
