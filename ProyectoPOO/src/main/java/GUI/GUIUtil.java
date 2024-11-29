package GUI;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.File;

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
    
    // Clase para cargar una ventana con botones
    public static class CambiaPanel {

        private JPanel container;
        private JPanel content;

        public CambiaPanel(JPanel container, JPanel content) {
            this.container = container;
            this.content = content;
            this.container.removeAll();
            this.container.revalidate();
            this.container.repaint();

            this.container.add(this.content);
            this.container.revalidate();
            this.container.repaint();
        }
    }
}
