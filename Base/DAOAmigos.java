package Base;
import java.sql.*;
import java.util.ArrayList;
/**
 * DAO que permite interactuar con la tabla de Amigos en la Base de Datos
 * @author Marcos Garcia y Daniel Fuentes
 */
public class DAOAmigos extends AbstractDAO {

   public DAOAmigos (Connection conexion){
        super.setConexion(conexion);
    }
   
   private boolean sonAmigos(String a1, String a2){
       Connection con;
        PreparedStatement stmAmigos=null;
        ResultSet rsAmigos;
        boolean existe = false;

        con=this.getConexion();

        try {
            stmAmigos=con.prepareStatement("select amigo1,amigo2 from amigos where amigo1 like ? and amigo2 like ?");
            stmAmigos.setString(1, a1);
            stmAmigos.setString(2, a2);
            rsAmigos=stmAmigos.executeQuery();
            
            if (rsAmigos.next()){
             existe = true;

            }
        } catch (SQLException e){
          System.out.println(e.getMessage());
          
        }finally{
          try {stmAmigos.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return existe;
   }
    
    public boolean insertarPeticion(String usuario, String amigo){
        boolean esInsertado = false;
        if(existeUsuario(amigo)){
            Connection con;
            PreparedStatement stmUsuario=null;

            con=super.getConexion();
          
            try {
                stmUsuario=con.prepareStatement("insert into peticiones(usuario,pedidor) "+ "values (?,?)");


                stmUsuario.setString(1, amigo);
                stmUsuario.setString(2, usuario);
                stmUsuario.executeUpdate();

                esInsertado = true;

            } catch (SQLException e){
              System.out.println(e.getMessage());
            }finally{
              try {stmUsuario.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
            }
        }
        return esInsertado;
    }
    
    private boolean existeUsuario(String nombre){
        Connection con;
        PreparedStatement stmCliente=null;
        ResultSet rsCliente;
        boolean existe = false;
        
        con=this.getConexion();

        try {
            stmCliente=con.prepareStatement("select nombre from usuario where nombre = ?");
            stmCliente.setString(1, nombre);
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
    
    public ArrayList<String> listaPeticiones(String nombre){
        ArrayList<String> peticiones = new ArrayList<>();
        Connection con;
        PreparedStatement stmPeticion=null;
        ResultSet rsPeticion;
        
        con=super.getConexion();
        
        String consulta = "select pedidor from peticiones where usuario like ?";
        
        try{
            stmPeticion = con.prepareStatement(consulta);
            stmPeticion.setString(1, nombre);
            
            rsPeticion = stmPeticion.executeQuery();
            
            while(rsPeticion.next()){
                peticiones.add(rsPeticion.getString("pedidor"));
            }
        }
        catch (SQLException e){
          System.out.println(e.getMessage());
        }
        finally{
          try {
               stmPeticion.close();
          } catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        
        return peticiones;   
    }
    
    public boolean añadirAmigo(String usuario, String amigo){
        Connection con;
        PreparedStatement stmAnadir1=null;
        PreparedStatement stmAnadir2=null;
        PreparedStatement stmDelPet=null;
        PreparedStatement stmPetCruzada=null;
        ResultSet rsCruzada=null;

        boolean exito = true;
        boolean cruzada = false;
        
        if(!sonAmigos(usuario, amigo)){

            con=super.getConexion();

            try{
                con.setAutoCommit(false);

                stmAnadir1=con.prepareStatement("insert into amigos(amigo1,amigo2) "+ "values (?,?)");
                stmAnadir2=con.prepareStatement("insert into amigos(amigo1,amigo2) "+ "values (?,?)");
                stmDelPet=con.prepareStatement("delete from peticiones where usuario like ? and pedidor like ?");
                stmPetCruzada=con.prepareStatement("select from peticiones where usuario like ? and pedidor like ?");
                
                stmPetCruzada.setString(1,usuario);
                stmPetCruzada.setString(2,amigo);
                rsCruzada = stmPetCruzada.executeQuery();
                
                if(rsCruzada.next()){
                    cruzada = true;
                }

                stmAnadir1.setString(1, usuario);
                stmAnadir1.setString(2, amigo);
                stmAnadir2.setString(1, amigo);
                stmAnadir2.setString(2, usuario);
                stmDelPet.setString(1, usuario);
                stmDelPet.setString(2, amigo);
                stmDelPet.executeUpdate();
                stmAnadir1.executeUpdate();
                stmAnadir2.executeUpdate();
                
                if(cruzada){
                    stmDelPet.setString(1, amigo);
                    stmDelPet.setString(2, usuario);
                    stmDelPet.executeUpdate();
                }

                con.commit();
            }catch (SQLException e){
                exito = false;
                System.out.println(e.getMessage());
                if(con!=null){
                    try {
                        con.rollback();
                    }catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }finally{
                try {stmAnadir1.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");} 
                try {stmAnadir2.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");} 
                try {stmDelPet.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");} 
            }
        } 
        return exito;
    }
    
    public ArrayList<String> obtenerAmigos(String usuario){
        Connection con;
        PreparedStatement stmCliente=null;
        ResultSet rsCliente;
        ArrayList<String> nom = new ArrayList<>();
        con=this.getConexion();

        try {
            stmCliente=con.prepareStatement("select amigo2 from amigos where amigo1 = ?");
            stmCliente.setString(1, usuario);
            rsCliente=stmCliente.executeQuery();
            while(rsCliente.next()){
                nom.add(rsCliente.getString(1));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {stmCliente.close();} catch (SQLException e){System.out.println("Imposible cerrar cursores");}
        }
        return nom;
    }
}
