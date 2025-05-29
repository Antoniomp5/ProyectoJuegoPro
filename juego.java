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
        System.out.println("2. Registro de jugadores (GUI)");
        String opcionInicial = sc.nextLine();
        
        /**
       	 * La invocacion en el main. 
       	 */

        if (opcionInicial.equals("2")) {
            SwingUtilities.invokeLater(() -> new RegistroJugadoresGUI().setVisible(true));
            return;
        }
        // La invocación de las bases de datos.
        BDManager bdSQL = null;
        BDPojoManager bdPOJO = new BDPojoManager();

        try {
            System.out.println("¡Bienvenido al Space Invaders!");
            System.out.println("Introduce tu nombre: ");
            String nombre = sc.nextLine();

            bdSQL = new BDManager();

            int jugadorId = bdSQL.buscarJugador(nombre);
            if (jugadorId == -1) {
                jugadorId = bdSQL.crearJugador(nombre);
                System.out.println("Nuevo jugador creado en SQL.");
            } else {
                System.out.println("Jugador encontrado en SQL.");
            }

            int jugadorPojoId = bdPOJO.buscarJugador(nombre);
            if (jugadorPojoId == -1) {
                jugadorPojoId = bdPOJO.crearJugador(nombre);
                System.out.println("Nuevo jugador creado en POJO.");
            } else {
                System.out.println("Jugador encontrado en POJO.");
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
                        case "space":
                            jugador.ganarPuntos(100);
                            System.out.println("¡Disparo realizado!");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } catch (NaveException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                // Actualización en ambos sistemas
                String estado = jugador.haGanado() ? "ganado" : jugador.haPerdido() ? "perdido" : "jugando";
                bdSQL.actualizarJugador(jugadorId, jugador.getPuntos(), jugador.getVidas(), estado);
                bdPOJO.actualizarJugador(jugadorPojoId, jugador.getPuntos(), jugador.getVidas(), estado);
            }

            if (jugador.haGanado()) {
                System.out.println("¡Felicidades, " + jugador.getNombre() + "! Has ganado.");
                bdSQL.guardarPartida(jugadorId, jugador.getPuntos(), "ganado");
                bdPOJO.guardarPartida(jugadorPojoId, jugador.getPuntos(), "ganado");
            } else {
                System.out.println("¡Lo siento, " + jugador.getNombre() + "! Has perdido.");
                bdSQL.guardarPartida(jugadorId, jugador.getPuntos(), "perdido");
                bdPOJO.guardarPartida(jugadorPojoId, jugador.getPuntos(), "perdido");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, introduce un número.");
        } catch (ConcurrentModificationException e) {
            System.out.println("Error al modificar la lista de meteoros.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (bdSQL != null) {
                try {
                    bdSQL.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            sc.close();
        }
    }
}
