/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Angel
 */
public class Area1 extends javax.swing.JPanel {
    private JPanel buttonPanel; // Panel para organizar los botones
    /**
     * Creates new form Area1
     */
    public Area1(String folderName) {
        initComponents();
        if(folderName.equals("A1")){
            titulo.setText("Físico Matemáticas e Ingenierías");
            titulo.setForeground(Color.red);
        }
        else if(folderName.equals("A2")){
            titulo.setText("Ciencias Biológicas, Químicas y de la Salud");
            titulo.setForeground(Color.GREEN);
        }
        else if(folderName.equals("A3")){
            titulo.setText("Ciencias Sociales");
            titulo.setForeground(Color.BLUE);
        }
        else{
            titulo.setText("Humanidades y de las Artes");
            titulo.setForeground(Color.MAGENTA);
        }
        cargarBotones(folderName);
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

                // Crear un botón por cada subcarpeta
                for (File subFolder : subFolders) {
                    JButton button = new JButton(subFolder.getName());
                    button.addActionListener(evt -> {
                        JOptionPane.showMessageDialog(this, 
                            "Has seleccionado: " + subFolder.getName(), 
                            "Información", 
                            JOptionPane.INFORMATION_MESSAGE);
                        button.setBackground(Color.GREEN);
                    });

                    // Configuramos un tamaño preferido para que los botones no se estiren demasiado
                    button.setPreferredSize(new Dimension(150, 40));

                    buttonPanel.add(button);
                }

                // Aseguramos que el panel de botones tenga el tamaño adecuado
                buttonPanel.revalidate();
                buttonPanel.repaint();

                // Aseguramos que el panel de botones se ajuste al JScrollPane
                scroll.setViewportView(buttonPanel);  // Reemplazamos el contenido del JScrollPane con el buttonPanel
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
                    .addComponent(titulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
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
    }//GEN-LAST:event_BorrarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarActionPerformed

    private void AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarActionPerformed
        // TODO add your handling code here:
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
