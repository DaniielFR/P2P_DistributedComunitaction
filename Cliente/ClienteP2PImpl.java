package Cliente;

import Base.FachadaBase;
import Servidor.ServidorP2PInterfaz;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import Usuario.*;

/**
 * Clase que inicializa la conexión y sirve como fachada para acceder a los
 * propios DAOs que acceden a la Base de Datos
 * @author Marcos Garcia y Daniel Fuentes
 */
public class ClienteP2PImpl extends UnicastRemoteObject implements ClienteP2PInterfaz {

    private ArrayList<Usuario> usuarios;
    private final VChat chatUsuario;
    private final FachadaBase FachadaBase;

    public ClienteP2PImpl(FachadaBase FachadaBase, ServidorP2PInterfaz servidor) throws RemoteException {
        super( );
        this.usuarios = new ArrayList<>();
        this.FachadaBase = FachadaBase;
        chatUsuario = new VChat(FachadaBase,servidor);
        chatUsuario.setVisible(false);
    }

    /**
     * Función que se usa para notificar el callback al cliente
     * @param usuarios
     * @param nombre
     * @return 
     */
    @Override
    public String notificarCliente(ArrayList<Usuario> usuarios, String nombre){

        String retorno = "Call back recibido. Usuarios: " + usuarios.toString();
        this.usuarios = usuarios;
        chatUsuario.setNombre(nombre);
        chatUsuario.setListaUsuarios(usuarios);
        return retorno;
    }

    /**
     * Metodo para enviar mensajes de un usuario a otro
     * @param mensaje
     * @param nombre
     * @return
     */
    @Override
    public boolean enviarMensajes(String mensaje, String nombre){
        chatUsuario.escribirMensaje(mensaje,nombre);
        return true;
    }
    
    /**
     * Metodo para Iniciar Visualizar la ventana del chat de un usuario
     */
    @Override
    public void IniciaChat(){
        chatUsuario.setVisible(true);
        
    }
}
