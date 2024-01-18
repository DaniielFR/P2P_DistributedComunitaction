package Servidor;
import java.rmi.*;
import Usuario.Usuario;

public interface ServidorP2PInterfaz extends Remote {

    /**
     * Permite a un cliente abandonar el registro de Callback
     * @param nombre
     * @throws java.rmi.RemoteException
     */
    public void salirCallback(String nombre) throws java.rmi.RemoteException;
    
    /**
     * Añade usuarios para el registro callback con el objetivo de que reciban
     * los callback pertinentes con sus listas de amigos actualizadas conforme
     * los usuarios que sean amigos se conecten
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public boolean iniciarSesionCallback(Usuario usuario) throws java.rmi.RemoteException;
    
    /**
     * Actualiza la lista de amigos de un usuario para tener en cuenta la nueva
     * relación de amistad entre el usuario y otro usuario
     * @param usuario
     * @param amigo
     * @throws RemoteException
     */
    public void nuevoAmigo(String usuario, String amigo) throws java.rmi.RemoteException;
    
}
