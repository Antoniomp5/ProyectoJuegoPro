package practica;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class AnnadirLeerFichero {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        String nombreTxT = "Hola.txt";
        // Pedir el texto a añadir
        System.out.print("Introduce el texto que deseas añadir: ");
        String texto = scanner.nextLine();

        // Escribir el texto al final del archivo
        try (FileWriter escritor = new FileWriter(nombreTxT, true)) {
            escritor.write("\n" + texto);
            System.out.println("Texto añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        // Leer y mostrar el contenido del archivo
        System.out.println("\nContenido actual del archivo:");
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreTxT))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        scanner.close();
    }
}
