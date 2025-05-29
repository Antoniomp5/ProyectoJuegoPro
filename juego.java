package juego;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Juego {
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Jugar");
        System.out.println("2. Registro de jugadores");
        String opcionInicial = sc.nextLine();

        if (opcionInicial.equals("2")) {
            SwingUtilities.invokeLater(() -> new RegistroJugadoresGUI().setVisible(true));
            return;
        }

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


                String entrada = sc.nextLine().trim().toLowerCase();

                try {
                    switch (entrada) {
                        case "a":
                            nave.moverIzquierda();
                            break;
                        case "d":
                            nave.moverDerecha();
                            break;
                        case "w":
                            nave.moverArriba();
                            break;
                        case "s":
                            nave.moverAbajo();
                            break;
                        case "":
                        case " ":
                        case "SPACE":
                            jugador.ganarPuntos(100);
                            System.out.println("¡Disparo realizado!");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } catch (NaveException e) {
                    System.out.println("Error: " + e.getMessage());
                }

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
