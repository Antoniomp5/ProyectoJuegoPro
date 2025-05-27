public class Juego {
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        try {
            System.out.println("¡Bienvenido al Space Invaders!");
            System.out.println("Introduce tu nombre: ");
            String nombre = sc.nextLine();
            Jugador jugador = new Jugador(nombre);

            Nave nave = new Nave();
            ArrayList<Meteoro> meteoros = new ArrayList<>();

            // Bucle principal del juego
            while (!jugador.haGanado() && !jugador.haPerdido()) {
                // Crear meteoros nuevos
                if (rand.nextInt(10) < 3) { // Cada ciclo tiene una pequeña probabilidad de generar un meteoro
                    meteoros.add(new Meteoro(rand.nextInt(10), 0)); // Meteoros en posiciones aleatorias
                }

                // Mover meteoros, y comprobar si impactan con la nave (esto es solo un ejemplo de lo que puede suceder)
                for (Meteoro meteoro : new ArrayList<>(meteoros)) { // Crear una copia de la lista para evitar ConcurrentModificationException
                    meteoro.mover();
                    // Verificar si meteoro tocó la nave
                    if (meteoro.getX() == nave.getX() && meteoro.getY() == nave.getY()) {
                        jugador.perderVida();
                        meteoros.remove(meteoro);
                        System.out.println("¡Impacto! Has perdido una vida.");
                    }
                }

                // Mostrar el estado del jugador y la nave
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
                            jugador.ganarPuntos(100); // Simula que el disparo destruye un meteoro y suma puntos
                            System.out.println("¡Disparo realizado!");
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                } catch (NaveException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Verificar si el jugador ha ganado o perdido
            if (jugador.haGanado()) {
                System.out.println("¡Felicidades, " + jugador.getNombre() + "! Has ganado.");
            } else {
                System.out.println("¡Lo siento, " + jugador.getNombre() + "! Has perdido.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, introduce un número.");
        } catch (ConcurrentModificationException e) {
            System.out.println("Error al modificar la lista de meteoros.");
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            e.printStackTrace(); // Mostrar detalles del error para depuración
        } finally {
            sc.close();
        }
    }
}
