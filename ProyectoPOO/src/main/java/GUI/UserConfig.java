/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import static GUI.GUIUtil.setEditable;
import Logic.CreadorDeUsuario;
import Logic.Usuario;
import Logic.Tutor;
import Logic.ArchivoUsuarios;
import java.awt.GridLayout;
import javax.swing.*;
import org.json.JSONObject;

/**
 *
 * @author Angel
 */
public class UserConfig extends javax.swing.JFrame {
    
    private static String mostrarPopUp() {
        // Crear un JOptionPane personalizado
        String v="NA";
        Object[] options = {"Básico ($0)", "Medio ($20)", "Premium ($50)", "Cancelar"};
        int opcion = JOptionPane.showOptionDialog(
                null,
                "Selecciona un plan de suscripción:",
                "Planes de Suscripción",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0] // opción predeterminada
        );

        // Manejar la opción seleccionada
        switch (opcion) {
            case 0: // Básico
                JOptionPane.showMessageDialog(null, "Has seleccionado el plan Básico. ¡No tiene costo!");
                v="NA";
                break;
            case 1: // Medio
                v=mostrarFormularioPago("Medio", 20);
                break;
            case 2: // Premium
                v=mostrarFormularioPago("Premium", 50);
                break;
            default:
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún plan.");
        }
        return v;
    }

    private static String mostrarFormularioPago(String plan, int costo) {
        // Crear campos de entrada
        JTextField cuentaField = new JTextField(15);
        JTextField cvvField = new JTextField(3);

        // Crear el panel para los campos
        JPanel pagoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        pagoPanel.add(new JLabel("Número de cuenta (16 dígitos):"));
        pagoPanel.add(cuentaField);
        pagoPanel.add(new JLabel("CVV (3 dígitos):"));
        pagoPanel.add(cvvField);

        // Mostrar el pop-up
        int result = JOptionPane.showConfirmDialog(
                null,
                pagoPanel,
                "Pago del Plan " + plan + " ($" + costo + ")",
                JOptionPane.OK_CANCEL_OPTION
        );

        // Procesar la entrada
        if (result == JOptionPane.OK_OPTION) {
            String cuenta = cuentaField.getText();
            String cvv = cvvField.getText();
            if(usuario.mejorarSuscripcion(cuenta, cvv, Usuario.Suscripcion.valueOf(plan))){
                JOptionPane.showMessageDialog(null, "¡Pago realizado con éxito para el plan " + plan + "!");
                return plan;
            }
            else{
                JOptionPane.showMessageDialog(null, "Error: Por favor, completa todos los campos.");
                return "NA";
            }
        }
        return "NA";
    }
    
    private static Usuario usuario;
    public UserConfig() {
        System.out.println("Llegando con:"+usuario.getTipoUsuario().toString());
        initComponents();
        tRango.setText(usuario.getTipoUsuario());
    }
    public static void setUsuario(Usuario usuario) {
        UserConfig.usuario = usuario;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tUser = new javax.swing.JTextField();
        tCorreo = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        tRango = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tRango1 = new javax.swing.JTextField();
        bEditarC = new javax.swing.JButton();
        bEditarP = new javax.swing.JButton();
        bEditarS = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Ravenscroft", 0, 48)); // NOI18N
        jLabel1.setText("Configuración");

        bGuardar.setBackground(new java.awt.Color(255, 204, 102));
        bGuardar.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        bGuardar.setText("Guardar Cambios");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Correo electrónico:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Contraseña:");

        tUser.setEditable(false);
        tUser.setText("Nombre");

        tCorreo.setEditable(false);
        tCorreo.setText("EMAIL");
        tCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCorreoActionPerformed(evt);
            }
        });

        jPasswordField1.setEditable(false);
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Rango:");

        tRango.setEditable(false);
        tRango.setText("USUARIO");
        tRango.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRangoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Suscripción:");

        tRango1.setEditable(false);
        tRango1.setText("USUARIO");
        tRango1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRango1ActionPerformed(evt);
            }
        });

        bEditarC.setBackground(new java.awt.Color(255, 204, 204));
        bEditarC.setText("EDIT");
        bEditarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarCActionPerformed(evt);
            }
        });

        bEditarP.setBackground(new java.awt.Color(255, 204, 204));
        bEditarP.setText("EDIT");
        bEditarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarPActionPerformed(evt);
            }
        });

        bEditarS.setBackground(new java.awt.Color(255, 204, 204));
        bEditarS.setText("EDIT");
        bEditarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarSActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 51, 0));
        jButton1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bGuardar)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tRango, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tRango1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(96, 96, 96)
                                .addComponent(tUser)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bEditarC, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bEditarP, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bEditarS, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bEditarC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bEditarP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tRango1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bEditarS)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(bGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        // TODO add your handling code here:
        String contraseña = new String(jPasswordField1.getPassword());
        usuario.editarCuenta(tCorreo.getText(), contraseña);
        
        JSONObject us = new JSONObject();

        us.put("contraseña", usuario.getContraseña());
        us.put("correo", usuario.getCorreo());
        us.put("nombre", usuario.getNombre());
        us.put("fechaNacimiento", usuario.getFechaNacimiento());
        us.put("sexo", usuario.getSexo());
        us.put("id", usuario.getId());
        us.put("tUser", usuario.getTipoUsuario());
        if (usuario instanceof Tutor) {
            Tutor tutor = (Tutor) usuario; // Convertir usuario a Tutor
            us.put("Materia", tutor.getMateria()); 
         }
         else{
            us.put("Materia","Na");
         }
        ArchivoUsuarios.actualizarUsuarioPorCorreo(tCorreo.getText(),us);
        Menu.setUsuario(usuario);
        GUIUtil.abrirVentana(Menu.class, this);
    }//GEN-LAST:event_bGuardarActionPerformed

    private void tCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCorreoActionPerformed

    private void tRangoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRangoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tRangoActionPerformed

    private void tRango1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRango1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tRango1ActionPerformed

    private void bEditarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarCActionPerformed
        // TODO add your handling code here:
        tCorreo.setEditable(true);
    }//GEN-LAST:event_bEditarCActionPerformed

    private void bEditarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarPActionPerformed
        // TODO add your handling code here:
        setEditable(jPasswordField1, true);
    }//GEN-LAST:event_bEditarPActionPerformed

    private void bEditarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarSActionPerformed
        // TODO add your handling code here:
        String v="NA";
        v=mostrarPopUp();
        if (!v.equals("NA")){
            tRango1.setText(v);
        }
        else{
            tRango1.setText("Estudiante");
            usuario.mejorarSuscripcion(v, v, Usuario.Suscripcion.Basico);
        }
    }//GEN-LAST:event_bEditarSActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu.setUsuario(usuario);
        GUIUtil.abrirVentana(Menu.class, this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        tUser.setText(usuario.getNombre());
        tCorreo.setText(usuario.getCorreo());
        jPasswordField1.setText(usuario.getContraseña());
        tRango1.setText(usuario.getTipoUsuario());
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditarC;
    private javax.swing.JButton bEditarP;
    private javax.swing.JButton bEditarS;
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField tCorreo;
    private javax.swing.JTextField tRango;
    private javax.swing.JTextField tRango1;
    private javax.swing.JTextField tUser;
    // End of variables declaration//GEN-END:variables
}
