package GUI;

import java.awt.Color;

public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pLogin = new javax.swing.JPanel();
        tInicioSesion = new javax.swing.JLabel();
        tContra = new javax.swing.JLabel();
        vUsuario = new javax.swing.JTextField();
        tUsuario1 = new javax.swing.JLabel();
        vContra = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        bContraV = new javax.swing.JToggleButton();
        bSignIn = new javax.swing.JButton();
        bSignUp = new javax.swing.JButton();
        bForgot = new javax.swing.JButton();
        tPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pLogin.setBackground(new java.awt.Color(255, 255, 255));
        pLogin.setToolTipText("");

        tInicioSesion.setFont(new java.awt.Font("Ravenscroft", 0, 36)); // NOI18N
        tInicioSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tInicioSesion.setText("Iniciar Sesión");

        tContra.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        tContra.setForeground(new java.awt.Color(39, 185, 39));
        tContra.setText("Contraseña:");

        vUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        vUsuario.setForeground(new java.awt.Color(153, 153, 153));
        vUsuario.setText("Ingrese su usuario");
        vUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 204, 51)));
        vUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        vUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vUsuarioFocusGained(evt);
            }
        });
        vUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vUsuarioActionPerformed(evt);
            }
        });

        tUsuario1.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        tUsuario1.setForeground(new java.awt.Color(39, 185, 39));
        tUsuario1.setText("Usuario:");

        vContra.setText("Ingrese una contraseña");
        vContra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(39, 185, 39)));
        vContra.setCaretColor(new java.awt.Color(39, 185, 39));
        vContra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                vContraFocusGained(evt);
            }
        });
        vContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vContraActionPerformed(evt);
            }
        });

        bContraV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/eyeV.png"))); // NOI18N
        bContraV.setBorderPainted(false);
        bContraV.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icon/eyeH.png"))); // NOI18N
        bContraV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bContraVActionPerformed(evt);
            }
        });

        bSignIn.setBackground(new java.awt.Color(39, 185, 39));
        bSignIn.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        bSignIn.setForeground(new java.awt.Color(255, 255, 255));
        bSignIn.setText("Continuar");
        bSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSignInActionPerformed(evt);
            }
        });

        bSignUp.setBackground(new java.awt.Color(255, 204, 153));
        bSignUp.setFont(new java.awt.Font("Myanmar Text", 1, 14)); // NOI18N
        bSignUp.setText("Crear Cuenta");
        bSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSignUpActionPerformed(evt);
            }
        });

        bForgot.setForeground(new java.awt.Color(0, 0, 255));
        bForgot.setText("Olvidé mi contraseña");
        bForgot.setBorder(null);
        bForgot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bForgotActionPerformed(evt);
            }
        });

        tPass.setEnabled(false);
        tPass.setFocusable(false);

        javax.swing.GroupLayout pLoginLayout = new javax.swing.GroupLayout(pLogin);
        pLogin.setLayout(pLoginLayout);
        pLoginLayout.setHorizontalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pLoginLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(bSignIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSignUp))
                    .addGroup(pLoginLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tContra)
                            .addComponent(vUsuario)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pLoginLayout.createSequentialGroup()
                                .addComponent(vContra, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bContraV))
                            .addComponent(tUsuario1)
                            .addComponent(bForgot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pLoginLayout.createSequentialGroup()
                                .addComponent(tPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(7, 7, 7)))))
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(tInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pLoginLayout.setVerticalGroup(
            pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pLoginLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tInicioSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tUsuario1)
                .addGap(3, 3, 3)
                .addComponent(vUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tContra)
                .addGap(1, 1, 1)
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bContraV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vContra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bForgot, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSignIn)
                    .addComponent(bSignUp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(tPass, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tInicioSesion.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vUsuarioActionPerformed

    private void bSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSignInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bSignInActionPerformed

    private void bSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSignUpActionPerformed
        // TODO add your handling code here:
        GUIUtil.abrirVentana(SignUp.class,this);
    }//GEN-LAST:event_bSignUpActionPerformed

    private void bForgotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bForgotActionPerformed
        // TODO add your handling code here:
        if(vUsuario.getText()!=""){
            //Buscar usuario en BD y poner en tPass
        }
    }//GEN-LAST:event_bForgotActionPerformed

    private void bContraVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bContraVActionPerformed
    if(bContraV.isSelected()){
        vContra.setEchoChar((char)0);
    } else {
        vContra.setEchoChar('*');
    }        
    }//GEN-LAST:event_bContraVActionPerformed

    private void vUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vUsuarioFocusGained
    // Cuando el cuadro de texto obtiene el foco
    if (vUsuario.getText().equals("Ingrese su usuario")) {
        vUsuario.setText(""); // Borra el contenido
        vUsuario.setForeground(Color.BLACK); // Cambia el color a negro
    }
    }//GEN-LAST:event_vUsuarioFocusGained

    private void vContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vContraActionPerformed

    private void vContraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vContraFocusGained
        if (vContra.getText().equals("Ingrese una contraseña")) {
            vContra.setText(""); // Borra el contenido
        }
    }//GEN-LAST:event_vContraFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bContraV;
    private javax.swing.JButton bForgot;
    private javax.swing.JButton bSignIn;
    private javax.swing.JButton bSignUp;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel pLogin;
    private javax.swing.JLabel tContra;
    private javax.swing.JLabel tInicioSesion;
    private javax.swing.JLabel tPass;
    private javax.swing.JLabel tUsuario1;
    private javax.swing.JPasswordField vContra;
    private javax.swing.JTextField vUsuario;
    // End of variables declaration//GEN-END:variables
}
