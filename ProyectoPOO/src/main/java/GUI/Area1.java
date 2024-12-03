/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Logic.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Angel
 */
public class Area1 extends javax.swing.JPanel {
    private JPanel buttonPanel; // Panel para organizar los botones
    private static Usuario usuario;
    private static String folder;
    /**
     * Creates new form Area1
     */
    public Area1(String folderName) {
        initComponents();
        jPanel1.setVisible(false);
        if(folderName.equals("A1")){
            titulo.setText("Físico Matemáticas e Ingenierías");
            titulo.setForeground(Color.red);
            folder = "A1";
        }
        else if(folderName.equals("A2")){
            titulo.setText("Ciencias Biológicas, Químicas y de la Salud");
            titulo.setForeground(Color.GREEN);
            folder = "A2";
        }
        else if(folderName.equals("A3")){
            titulo.setText("Ciencias Sociales");
            titulo.setForeground(Color.BLUE);
            folder = "A3";
        }
        else{
            titulo.setText("Humanidades y de las Artes");
            titulo.setForeground(Color.MAGENTA);
            folder = "A4";
        }
        cargarBotones(folderName);
        if ((usuario instanceof Tutor && ((Tutor) usuario).getMateria().toString().equals(Usuario.Materia.valueOf(folderName).toString() )) || usuario instanceof Administrador){
            jPanel1.setVisible(true);
        }
    }
    public static void setUsuario(Usuario usuario) {
        Area1.usuario = usuario;
    }
    private void cargarBotones(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] subFolders = folder.listFiles(File::isDirectory); // Filtrar solo directorios

            if (subFolders != null) {
                // Crear un panel para contener los botones
                JPanel buttonPanel = new JPanel();
                // Configuramos un GridLayout con 3 columnas (hasta 3 botones por fila)
                buttonPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 botones por fila, espaciado de 10 píxeles entre ellos

                // Lista para almacenar los progresos
                List<Progreso<Curso>> progresos;
                progresos = new ArrayList<>();

                // Crear un botón y un progreso por cada subcarpeta
                for (File subFolder : subFolders) {
                    File datosFile = new File(subFolder, "datos.txt");

                    if (datosFile.exists()) {
                        try {
                            // Leer el archivo datos.txt
                            String jsonContent = new String(Files.readAllBytes(datosFile.toPath()));
                            JSONObject jsonObject = new JSONObject(jsonContent);

                            // Extraer datos del JSON
                            String titulo = jsonObject.getString("titulo");
                            String descripcion = jsonObject.getString("descripcion");
                            String temario = jsonObject.getString("temario");
                            String autor = jsonObject.getString("autor");

                            // Crear una instancia de Curso
                            Curso curso = new Curso(titulo, descripcion, temario, autor);
                            JButton button = new JButton(titulo);
                            Boolean progresoCompletado = false;
                            // Crear una instancia de Progreso para el curso
                            Progreso<Curso> progreso = new Progreso<>(titulo, curso);
                            progresos.add(progreso); // Añadirlo a la lista de progresos

                            for (Object obj : usuario.getProgresos()) {
                                // Hacemos un cast explícito a Progreso<?>
                                if (obj instanceof Progreso<?>) {
                                    Progreso<?> p = (Progreso<?>) obj;
                                    if (p.getTitulo().equals(subFolder.getName())) {
                                        System.out.println("Este curso ya se tomó!");
                                        button.setBackground(Color.GREEN);
                                        progresoCompletado = true;
                                    }
                                }
                            }

                            button.addActionListener(evt -> {
                            // Lógica al presionar el botón
                            if (button.getBackground()==Color.GREEN) {
                                JOptionPane.showMessageDialog(this,
                                    "El curso " + curso.gettitulo() + " ya fue completado.",
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this,
                                    "Has seleccionado el curso: " + curso.gettitulo(),
                                    "Información",
                                    JOptionPane.INFORMATION_MESSAGE);
                                button.setBackground(Color.GREEN);

                                // Llamar al método agregarProgreso del usuario
                                if (usuario != null) {
                                    progreso.actualizarProgreso(100, curso.gettitulo());
                                    usuario.agregarProgreso(progreso);
                                    JOptionPane.showMessageDialog(this,
                                        "Progreso del curso " + curso.gettitulo() + " agregado al usuario.",
                                        "Información",
                                        JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this,
                                        "Error: Usuario no definido.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            });

                            // Configuramos un tamaño preferido para que los botones no se estiren demasiado
                            button.setPreferredSize(new Dimension(150, 40));

                            buttonPanel.add(button);
                        } catch (IOException | JSONException e) {
                            JOptionPane.showMessageDialog(this, 
                                "Error al leer el archivo datos.txt en la carpeta: " + subFolder.getName(), 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, 
                            "La carpeta " + subFolder.getName() + " no contiene un archivo datos.txt.", 
                            "Advertencia", 
                            JOptionPane.WARNING_MESSAGE);
                    }
                }

                // Aseguramos que el panel de botones tenga el tamaño adecuado
                buttonPanel.revalidate();
                buttonPanel.repaint();

                // Aseguramos que el panel de botones se ajuste al JScrollPane
                scroll.setViewportView(buttonPanel); // Reemplazamos el contenido del JScrollPane con el buttonPanel
                } else {
                JOptionPane.showMessageDialog(this, 
                    "La carpeta está vacía o no tiene subcarpetas.", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
                }
        } else {
            JOptionPane.showMessageDialog(this, 
                "El directorio especificado no existe: " + folderPath, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarBotonesPorCorreo(String folderPath, Usuario usuario, boolean op) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] subFolders = folder.listFiles(File::isDirectory); // Filtrar solo directorios

            if (subFolders != null) {
                // Crear un panel para contener los botones
                JPanel buttonPanel = new JPanel();
                // Configuramos un GridLayout con 3 columnas (hasta 3 botones por fila)
                buttonPanel.setLayout(new GridLayout(0, 3, 10, 10));

                // Crear un botón por cada subcarpeta que coincida con el correo
                for (File subFolder : subFolders) {
                    File datosFile = new File(subFolder, "datos.txt");

                    if (datosFile.exists()) {
                        try {
                            // Leer el archivo datos.txt
                            String content = new String(Files.readAllBytes(datosFile.toPath()));
                            JSONObject jsonData = new JSONObject(content);

                            // Verificar si el correo coincide con el usuario actual
                            if (jsonData.has("autor") && jsonData.getString("autor").equals(usuario.getCorreo())) {
                                // Crear botón
                                JButton button = new JButton(subFolder.getName());
                                if(op){
                                button.addActionListener(evt -> {
                                    // Subir un nuevo archivo
                                    JFileChooser fileChooser = new JFileChooser();
                                    fileChooser.setDialogTitle("Seleccionar un nuevo archivo");
                                    int result = fileChooser.showOpenDialog(this);

                                    if (result == JFileChooser.APPROVE_OPTION) {
                                        File selectedFile = fileChooser.getSelectedFile();
                                        try {
                                            // Copiar el archivo seleccionado a la carpeta
                                            Path destino = new File(subFolder, selectedFile.getName()).toPath();
                                            Files.copy(selectedFile.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
                                            JOptionPane.showMessageDialog(this, 
                                                "El archivo se ha subido correctamente a la carpeta: " + subFolder.getName(), 
                                                "Éxito", 
                                                JOptionPane.INFORMATION_MESSAGE);
                                        } catch (IOException e) {
                                            JOptionPane.showMessageDialog(this, 
                                                "Error al subir el archivo: " + e.getMessage(), 
                                                "Error", 
                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });}
                                else{
                                    button.addActionListener(e -> {
                                    int confirm = JOptionPane.showConfirmDialog(
                                        button,
                                        "¿Estás seguro de que deseas eliminar la carpeta: " + subFolder.getName() + "?",
                                        "Confirmar eliminación",
                                        JOptionPane.YES_NO_OPTION
                                    );

                                    if (confirm == JOptionPane.YES_OPTION) {
                                        try {
                                            GUIUtil.deleteFolder(subFolder); // Llamar al método para borrar la carpeta
                                            JOptionPane.showMessageDialog(button, 
                                                "La carpeta " + subFolder.getName() + " ha sido eliminada correctamente.", 
                                                "Éxito", 
                                                JOptionPane.INFORMATION_MESSAGE);
                                            buttonPanel.remove(button); // Eliminar el botón correspondiente
                                            buttonPanel.revalidate();
                                            buttonPanel.repaint();
                                        } catch (IOException ex) {
                                            JOptionPane.showMessageDialog(button, 
                                                "Error al eliminar la carpeta: " + ex.getMessage(), 
                                                "Error", 
                                                JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }); 
                                }

                                // Configurar tamaño preferido para que no se estiren demasiado
                                button.setPreferredSize(new Dimension(150, 40));
                                buttonPanel.add(button);
                            }
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(this, 
                                "Error al leer el archivo datos.txt en la carpeta: " + subFolder.getName(), 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                // Aseguramos que el panel de botones tenga el tamaño adecuado
                buttonPanel.revalidate();
                buttonPanel.repaint();

                // Aseguramos que el panel de botones se ajuste al JScrollPane
                scroll.setViewportView(buttonPanel);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "La carpeta está vacía o no tiene subcarpetas.", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "El directorio especificado no existe: " + folderPath, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        Borrar = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Agregar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        titulo.setFont(new java.awt.Font("Ravenscroft", 0, 48)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 51, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Físico Matemáticas e Ingenierías");

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new java.awt.Dimension(50, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setEnabled(false);

        Borrar.setBackground(new java.awt.Color(255, 102, 102));
        Borrar.setText("Borrrar");
        Borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarActionPerformed(evt);
            }
        });

        Editar.setBackground(new java.awt.Color(255, 255, 102));
        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        Agregar.setBackground(new java.awt.Color(102, 255, 51));
        Agregar.setText("Agregar");
        Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Agregar)
                .addGap(18, 18, 18)
                .addComponent(Editar)
                .addGap(18, 18, 18)
                .addComponent(Borrar)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Borrar)
                    .addComponent(Editar)
                    .addComponent(Agregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarActionPerformed
        // TODO add your handling code here:
        Agregar.setBackground(Color.GRAY);
        Editar.setBackground(Color.GRAY);
        Borrar.setBackground(Color.RED);
        String rutaBase = System.getProperty("user.dir");
        rutaBase += "\\" + folder;
        cargarBotonesPorCorreo(rutaBase,usuario,false);
        
    }//GEN-LAST:event_BorrarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
        Agregar.setBackground(Color.GRAY);
        Borrar.setBackground(Color.GRAY);
        Editar.setBackground(Color.YELLOW);
        String rutaBase = System.getProperty("user.dir");
        rutaBase += "\\" + folder;
        cargarBotonesPorCorreo(rutaBase,usuario,true);
        
    }//GEN-LAST:event_EditarActionPerformed

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        // TODO add your handling code here:
        Borrar.setBackground(Color.GRAY);
        Editar.setBackground(Color.GRAY);
        Agregar.setBackground(Color.GREEN);
        JFrame frame = new JFrame("Gestión de Temas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setVisible(true);
        String rutaBase = System.getProperty("user.dir");
        rutaBase += "\\" + folder;  // Concatenar la ruta actual con el nombre de la materia
       
        GUIUtil.gestionarTema(frame, rutaBase, usuario);
        frame.setVisible(false);
        cargarBotones(rutaBase);
        
    }//GEN-LAST:event_AgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregar;
    private javax.swing.JButton Borrar;
    private javax.swing.JButton Editar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
