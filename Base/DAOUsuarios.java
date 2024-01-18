package Base;
import java.sql.*;

/**
 * DAO que permite interactuar con la tabla de Usuarios en la Base de Datos
 * @author Marcos Garcia y Daniel Fuentes
 */
public class DAOUsuarios extends AbstractDAO {

    public DAOUsuarios (Connection conexion){
        super.setConexion(conexion);
    }
    
    public boolean insertarUsuario(String nombre, String contrasenha){
        Connection con;
        PreparedStatement stmUsuario=null;
        
        con=super.getConexion();
        
        boolean esInsertado = false;
        try {
            stmUsuario=con.prepareStatement("insert into usuario(nombre, contrasenha) "+
                                      "values (?,?)");
 
            stmUsuario.setString(1, nombre);
            stmUsuario.setString(2, contrasenha);
            stmUsuario.executeUpdate();

            esInsertado = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return esInsertado;
    }
    
    public boolean IniciarSesion(String nombre, String clave){
        Connection con;
        PreparedStatement stmCliente=null;
        ResultSet rsCliente;
        boolean existe = false;

        con=this.getConexion();

        try {
            stmCliente=con.prepareStatement("select nombre, contrasenha from usuario where nombre = ? and contrasenha = ?");
            stmCliente.setString(1, nombre);
            stmCliente.setString(2, clave);
            rsCliente=stmCliente.executeQuery();
            
            if (rsCliente.next()){
                existe = true;
            }
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
          
        }finally{
            try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return existe;
    }

    
    public String NombreUsuario(String id){
        Connection con;
        PreparedStatement stmCliente=null;
        ResultSet rsCliente;
        String nom = null;

        con=this.getConexion();

        try {
            stmCliente=con.prepareStatement("select nombre from usuario where id_cliente = ?");
            stmCliente.setString(1, id);
            rsCliente=stmCliente.executeQuery();
            
            if (rsCliente.next()){
                nom = rsCliente.getString("nombre");
            }
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return nom;
    }
       
    public boolean cambiarClave(String usuario, String clave){
        Connection con;
        PreparedStatement stmClave = null;
        boolean exito = true;
        
        con = super.getConexion();
        
        try{
            stmClave = con.prepareStatement("update usuario set contrasenha = ? where nombre like ?");
            stmClave.setString(1, clave);
            stmClave.setString(2, usuario);
            
            stmClave.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            exito = false;
        } 
        finally {
            try { stmClave.close();} catch (SQLException e) {System.out.println("Imposible cerrar cursores");}
        }
        return exito;
    }
}
