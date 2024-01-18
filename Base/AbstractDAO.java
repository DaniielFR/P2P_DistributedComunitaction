package Base;

/**
 * Abstracción de un DAO que permite interactuar con tablas de una Base de Datos
 * @author Marcos Garcia y Daniel Fuentes
 */
public abstract class AbstractDAO {
   private java.sql.Connection conexion;

   
    protected java.sql.Connection getConexion(){
        return this.conexion;
    }
    
    protected void setConexion(java.sql.Connection conexion){
        this.conexion=conexion;
    }
   
   protected boolean isNullOrEmpty(String str){
        if (str == null) return true;
        if (str.isEmpty()) return true;
        return false;
    }
   
   
}
