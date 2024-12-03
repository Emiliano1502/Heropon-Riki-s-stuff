package GUI;


import Logic.Usuario;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.JSONObject;



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
    
    public static boolean gestionarTema(JFrame frame, String rutaBase, Usuario u) {
        // Crear un JFileChooser para seleccionar el archivo
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar un archivo de página web");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos HTML y CSS", "html", "css");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();

            // Verificar si es un archivo válido
            if (!archivoSeleccionado.isFile()) {
                JOptionPane.showMessageDialog(frame, "Por favor, selecciona un archivo válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Solicitar un nombre para la carpeta
            String nombreCarpeta = JOptionPane.showInputDialog(frame, "Introduce un nombre para el curso:", "Crear Carpeta", JOptionPane.PLAIN_MESSAGE);
            String dsc = JOptionPane.showInputDialog(frame, "Introduce una descripción para el curso:", "Datos del curso", JOptionPane.PLAIN_MESSAGE);
            String temario = JOptionPane.showInputDialog(frame, "Temario separado por comas: ", "Datos del curso", JOptionPane.PLAIN_MESSAGE);

            if (nombreCarpeta == null || nombreCarpeta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "El nombre de la carpeta no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Crear la carpeta en la ruta A1
            File carpetaDestino = new File(rutaBase,"/" + nombreCarpeta);
            if (!carpetaDestino.exists() && !carpetaDestino.mkdirs()) {
                JOptionPane.showMessageDialog(frame, "No se pudo crear la carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Copiar o mover el archivo al interior de la carpeta
            try {
                Path archivoDestino = carpetaDestino.toPath().resolve(archivoSeleccionado.getName());
                Files.copy(archivoSeleccionado.toPath(), archivoDestino, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(frame, "Archivo movido exitosamente a la carpeta: " + carpetaDestino.getPath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                JSONObject json = new JSONObject();
                json.put("titulo", nombreCarpeta);
                json.put("descripcion", dsc);
                json.put("autor", u.getCorreo());
                json.put("temario", temario);
                File file = new File(carpetaDestino, "datos.txt");
                try (FileWriter writer = new FileWriter(file)) {
                    // Escribir el JSON en el archivo
                    writer.write(json.toString(4)); // La '4' es para una indentación legible
                    System.out.println("Archivo 'datos.txt' creado y guardado correctamente.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "No se pudo mover el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Operación cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
            return false;
    }
    
    public static void deleteFolder(File folder) throws IOException {
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                deleteFolder(file); // Llamada recursiva para eliminar subdirectorios y archivos
            }
        }
        Files.delete(folder.toPath()); // Borrar el directorio o archivo
    }
}
