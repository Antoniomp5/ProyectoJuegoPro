package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class RegistroJugadoresGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoNombre, campoPuntuacion;
    private JTextArea areaJugadores;
    private ArrayList<String> jugadores;

    public RegistroJugadoresGUI() {
        setTitle("Registro de Jugadores");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        jugadores = new ArrayList<>();

        // Crear componentes
        JLabel labelNombre = new JLabel("Nombre:");
        campoNombre = new JTextField(15);

        JLabel labelPuntuacion = new JLabel("Puntuación:");
        campoPuntuacion = new JTextField(5);

        JButton botonGuardar = new JButton("Guardar");
        JButton botonMostrar = new JButton("Mostrar Todos");

        areaJugadores = new JTextArea(10, 30);
        areaJugadores.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaJugadores);

        // Panel de entrada
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridLayout(3, 2));
        panelEntrada.add(labelNombre);
        panelEntrada.add(campoNombre);
        panelEntrada.add(labelPuntuacion);
        panelEntrada.add(campoPuntuacion);
        panelEntrada.add(botonGuardar);
        panelEntrada.add(botonMostrar);

        // Añadir componentes a la ventana
        setLayout(new BorderLayout());
        add(panelEntrada, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Evento botón Guardar
        botonGuardar.addActionListener(e -> guardarJugador());

        // Evento botón Mostrar
        botonMostrar.addActionListener(e -> mostrarJugadores());
    }

    private void guardarJugador() {
        String nombre = campoNombre.getText();
        String puntuacionStr = campoPuntuacion.getText();

        if (nombre.isEmpty() || puntuacionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        try {
            int puntuacion = Integer.parseInt(puntuacionStr);
            String registro = nombre + " - " + puntuacion;
            jugadores.add(registro);

            // Guardar en archivo
            try (PrintWriter pw = new PrintWriter(new FileWriter("jugadores.txt", true))) {
                pw.println(registro);
            }

            campoNombre.setText("");
            campoPuntuacion.setText("");
            JOptionPane.showMessageDialog(this, "Jugador guardado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La puntuación debe ser un número.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar en el archivo.");
        }
    }

    private void mostrarJugadores() {
        areaJugadores.setText("");
        try (BufferedReader br = new BufferedReader(new FileReader("jugadores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                areaJugadores.append(linea + "\n");
            }
        } catch (IOException ex) {
            areaJugadores.setText("No se pudo leer el archivo.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroJugadoresGUI().setVisible(true));
    }
}
