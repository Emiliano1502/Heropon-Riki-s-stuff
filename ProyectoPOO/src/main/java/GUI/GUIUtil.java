
package GUI;

import javax.swing.JFrame;


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
}
