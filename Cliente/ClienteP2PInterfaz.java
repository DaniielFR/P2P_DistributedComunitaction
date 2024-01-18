package Cliente;
import java.util.ArrayList;
import Usuario.*;
import java.rmi.RemoteException;

/**
 * Interfaz remota para realizar Callback
 */

public interface ClienteP2PInterfaz extends java.rmi.Remote{

    /**
     * Método Remoto usado por el servidor para hacer un callback a un cliente
     * que implemente esta interfaz.
     * @param usuarios
     * @param nombre
     * @return
     * @throws RemoteException
     */
    public String notificarCliente(ArrayList<Usuario> usuarios, String nombre)throws java.rmi.RemoteException;
    
    /**
     * Metodo para Iniciar Visualizar la ventana del chat de un usuario
     * @throws RemoteException
     */
    public void IniciaChat() throws java.rmi.RemoteException;

    /**
     * Metodo para emviar mensajes de un usuario a otro
     * @param mensaje
     * @param nombre
     * @return
     * @throws RemoteException
     */
    public boolean enviarMensajes(String mensaje, String nombre) throws java.rmi.RemoteException;
}
