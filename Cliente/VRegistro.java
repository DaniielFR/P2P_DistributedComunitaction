package Cliente;

import Base.FachadaBase;
import Servidor.*;
import javax.swing.JOptionPane;

/**
 * Interfaz para el registro de un usuario
 * @author Marcos Garcia y Daniel Fuentes
 */
public class VRegistro extends javax.swing.JFrame {

    private VInicio vi;
    private ServidorP2PInterfaz servidor;
    private ClienteP2PInterfaz usuario;
    private FachadaBase FachadaBase;
    
    public VRegistro(VInicio vi,  ServidorP2PInterfaz h, ClienteP2PInterfaz usuario, FachadaBase FachadaBase) {
        this.servidor = h;
        this.usuario = usuario;
        this.vi = vi;
        this.FachadaBase = FachadaBase;
        initComponents();
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Atras = new javax.swing.JButton();
        Siguiente = new javax.swing.JButton();
        Usuario1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Clave2 = new javax.swing.JPasswordField();
        Clave1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Atras.setText("Atrás");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        Siguiente.setText("Siguiente");
        Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SiguienteActionPerformed(evt);
            }
        });

        Usuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Usuario1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre de usuario");

        jLabel2.setText("Contraseña");

        jLabel3.setText("Repite la contraseña");

        Clave2.setText("jPasswordField1");

        Clave1.setText("jPasswordField2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Siguiente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3))
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Usuario1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(Clave2)
                            .addComponent(Clave1))
                        .addGap(0, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Clave2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Clave1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Siguiente)
                    .addComponent(Atras))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Usuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Usuario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Usuario1ActionPerformed

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        vi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AtrasActionPerformed

    private void SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SiguienteActionPerformed
        // TODO add your handling code here:
        
        String nombre = Usuario1.getText();
        String clave1 = Clave1.getText();
        String clave2 = Clave2.getText();
        
        if(nombre!=null){
            if(nombre.length()<4){
                JOptionPane.showMessageDialog(null,"La longitud del nombre debe ser mayor que 4.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                if(clave1 != null && clave2 != null && clave1.trim().equals(clave2.trim())){
                    if(clave1.length()<4){
                        JOptionPane.showMessageDialog(null,"La longitud de la contraseña debe ser mayor que 4.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(registrarUsuario(nombre, clave1)){
                            JOptionPane.showMessageDialog(null,"El usuario ha sido creado correctamente.\n", "USUARIO REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
                            vi.setVisible(true);
                            this.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Ya existe ese nombre de usuario.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                            System.out.println("Ya existe ese nombre de usuario");
                        }
                            
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Debes introducir dos veces la contraseña y deben ser iguales.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Debes indicar un nombre de usuario.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SiguienteActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atras;
    private javax.swing.JPasswordField Clave1;
    private javax.swing.JPasswordField Clave2;
    private javax.swing.JButton Siguiente;
    private javax.swing.JTextField Usuario1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
public synchronized boolean registrarUsuario(String nombre, String contrasenha){
    
    return FachadaBase.insertarUsuario(nombre, contrasenha);
        
}


}
