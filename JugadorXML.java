package juego;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.*;
import org.w3c.dom.*;

import java.io.File;

public class JugadorXML {

    public static void guardarJugador(Jugador j) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element raiz = doc.createElement("Jugador");
            doc.appendChild(raiz);

            Element nombre = doc.createElement("Nombre");
            nombre.appendChild(doc.createTextNode(j.getNombre()));
            raiz.appendChild(nombre);

            Element vidas = doc.createElement("Vidas");
            vidas.appendChild(doc.createTextNode(String.valueOf(j.getVidas())));
            raiz.appendChild(vidas);

            Element puntos = doc.createElement("Puntuacion");
            puntos.appendChild(doc.createTextNode(String.valueOf(j.getPuntos())));
            raiz.appendChild(puntos);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("jugador.xml"));
            transformer.transform(source, result);

            System.out.println("Jugador guardado en jugador.xml");

        } catch (Exception e) {
            System.out.println("Error al guardar XML: " + e.getMessage());
        }
    }
}
