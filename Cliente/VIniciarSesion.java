package Cliente;

import Base.FachadaBase;
import Servidor.ServidorP2PInterfaz;
import Usuario.Usuario;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Interfaz para el inicio de sesión por parte de un usuario
 * @author Marcos Garcia y Daniel Fuentes
 */
public class VIniciarSesion extends javax.swing.JFrame {

    private VInicio vi;
    private ServidorP2PInterfaz servidor;
    private ClienteP2PInterfaz usuario;
    private FachadaBase FachadaBase;
    
    public VIniciarSesion(VInicio vi,  ServidorP2PInterfaz h, ClienteP2PInterfaz usuario, FachadaBase FachadaBase){
        this.servidor = h;
        this.usuario = usuario;
        this.vi = vi;
        this.FachadaBase = FachadaBase;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        initComponents();
        this.Error.setVisible(false);
        setLocationRelativeTo(null);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Siguiente = new javax.swing.JToggleButton();
        Atras = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Error = new javax.swing.JLabel();
        Usuario1 = new javax.swing.JTextField();
        Clave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Siguiente.setText("Siguiente");
        Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SiguienteActionPerformed(evt);
            }
        });

        Atras.setText("Atrás");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre de usuario");

        jLabel2.setText("Clave");

        Error.setForeground(java.awt.Color.red);
        Error.setText("jLabel3");

        Clave.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Siguiente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(Error))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Usuario1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(Clave))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Usuario1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(Error)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Siguiente)
                    .addComponent(Atras))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        vi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AtrasActionPerformed

    private void SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SiguienteActionPerformed
        // TODO add your handling code here:
        this.Error.setVisible(false);
        String nombre = Usuario1.getText();
        String contrasenha = Clave.getText();
        
        if(nombre!=null){
            if(nombre.length()<4){
                this.Error.setText("La longitud del nombre debe ser mayor que 4");
                this.Error.setVisible(true);
            }else{
                if(contrasenha!= null){
                    if(contrasenha.length()<4){
                        this.Error.setText("La longitud de la contrasenha debe ser mayor que 4");
                        this.Error.setVisible(true);
                    }else{
                        try {
                            if(FachadaBase.iniciarSesion(nombre, contrasenha)){
                                ArrayList<String> amigos = FachadaBase.obtenerAmigos(nombre);
                                if(servidor.iniciarSesionCallback(new Usuario(usuario, nombre,amigos))){
                                    usuario.IniciaChat();
                                    this.dispose();
                                }
                                
                            }else{
                                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                                System.out.println("El usuario o la contraseña son incorrecto");
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(VIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            
                    }
                }else{
                     this.Error.setText("Contraseña No Introducido");
                    this.Error.setVisible(true);
                }
            }
        }else{
            this.Error.setText("Usuario No Introducido");
            this.Error.setVisible(true);
        }
    }//GEN-LAST:event_SiguienteActionPerformed

    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Atras;
    private javax.swing.JPasswordField Clave;
    private javax.swing.JLabel Error;
    private javax.swing.JToggleButton Siguiente;
    private javax.swing.JTextField Usuario1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
