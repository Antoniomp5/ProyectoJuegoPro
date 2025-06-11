package practica;

import java.io.IOException;

public class FlujoEstandarSimple {
    public static void main(String[] args) throws IOException {
        System.out.println("Introduce cuatro caracteres:");

        // Leer cuatro caracteres desde el teclado
        char c1 = (char) System.in.read();
        char c2 = (char) System.in.read();
        char c3 = (char) System.in.read();
        char c4 = (char) System.in.read();

        // Imprimir usando distintos métodos
        System.out.write(c1);     // Sin salto de línea
        System.out.print(c2);     // Sin salto de línea
        System.out.println(c3);   // Con salto de línea
        System.out.printf("%c%n", c4); // Con salto de línea

        System.out.flush(); // Asegura que se impriman todos los caracteres
    }
}