package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Clase que inicializa la conexión y sirve como fachada para acceder a los
 * propios DAOs que acceden a la Base de Datos
 * @author Marcos Garcia y Daniel Fuentes
 */
public class FachadaBase {

    private java.sql.Connection conexion;
    private DAOUsuarios daoUsuarios;
    private DAOAmigos daoAmigos;

    public FachadaBase (){
        
        Properties configuracion = new Properties();
        
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();
            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);

            daoUsuarios = new DAOUsuarios(conexion);
            daoAmigos = new DAOAmigos(conexion);

        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
        }
             
    }
    
    public boolean insertarUsuario(String nombre, String contrasenha){
        return daoUsuarios.insertarUsuario(nombre,contrasenha);
    }
    
    public boolean iniciarSesion(String nombre, String contrasenha){
        return daoUsuarios.IniciarSesion(nombre, contrasenha);
    }
    
    public boolean insertarPeticion(String usuario, String amigo){
         return daoAmigos.insertarPeticion(usuario, amigo);
    }
    
    public ArrayList<String> listaPeticiones(String nombre){
        return daoAmigos.listaPeticiones(nombre);
    }
    
    public boolean anadirAmigo(String usuario, String amigo){
        return daoAmigos.añadirAmigo(usuario, amigo);
    }
     
    public ArrayList<String> obtenerAmigos(String usuario){
        return daoAmigos.obtenerAmigos(usuario);
    }
    
    public boolean cambiarClave(String usuario, String clave){
        return daoUsuarios.cambiarClave(usuario, clave);
    }
}
