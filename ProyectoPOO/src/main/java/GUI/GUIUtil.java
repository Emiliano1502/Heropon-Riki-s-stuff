package GUI;


import java.awt.BorderLayout;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class GUIUtil {

    public static void abrirVentana(Class<? extends JFrame> ventanaClase, JFrame ventanaActual) {
        try {
            // Cierra la ventana actual
            ventanaActual.dispose();

            // Crea una nueva instancia de la clase que se pasa como parámetro
            JFrame ventana = ventanaClase.getDeclaredConstructor().newInstance();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setEditable(JComponent componente, boolean editable) {
        if (componente instanceof JPasswordField) {
            JPasswordField passwordField = (JPasswordField) componente;
            passwordField.setEditable(editable);
        } else if (componente instanceof JLabel) {
            JLabel label = (JLabel) componente;
            // Para editar el texto, podemos hacer lo siguiente:
            if (editable) {
                // Reemplaza JLabel por un JTextField para permitir edición
                JTextField textField = new JTextField(label.getText());
                // Aquí agregarías el código para reemplazar el JLabel por el JTextField en el contenedor
            } else {
                // Vuelve a mostrar el JLabel
                label.setText(label.getText());
            }
        }
    }
}

class MostrarHTMLPanel extends JPanel {

    public MostrarHTMLPanel() {
        setLayout(new BorderLayout());

        // Crear un JEditorPane para cargar el HTML
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false); // Evitar que sea editable

        try {
            // Cargar el archivo HTML desde la carpeta A1
            File archivoHTML = new File("A1/archivo.html"); // Cambia "archivo.html" al nombre de tu archivo
            editorPane.setPage(archivoHTML.toURI().toURL());
        } catch (IOException e) {
            editorPane.setText("No se pudo cargar el archivo HTML.");
            e.printStackTrace();
        }

        // Agregar un JScrollPane para manejar el desplazamiento
        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane, BorderLayout.CENTER);
    }
}
