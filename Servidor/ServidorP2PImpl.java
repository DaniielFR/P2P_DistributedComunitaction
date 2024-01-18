package Servidor;

import Cliente.ClienteP2PInterfaz;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import Usuario.*;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class ServidorP2PImpl extends UnicastRemoteObject implements ServidorP2PInterfaz {

    private final HashMap<String, Usuario> listaClientes;

    public ServidorP2PImpl() throws RemoteException {
        super();
        listaClientes = new HashMap<>();
    }

    /**
     * Permite a un cliente abandonar el registro de Callback
     * @param nombre
     */
    @Override
    public synchronized void salirCallback(String nombre) {
        if (listaClientes.containsKey(nombre)) {
            listaClientes.remove(nombre, listaClientes.get(nombre));
            try {
                hacerCallback();
            } catch (RemoteException e) {
                System.out.println("Error saliendo del callback: " + e);
            }
            System.out.println("Cliente abandona el registro");
        } else {
            System.out.println("El cliente no puede abandonar el callback, ya que este no estaba registrado");
        }
    }
    
    /**
     * Realiza los callbacks a los usuarios registrados para el callback
     * @throws RemoteException
     */
    private synchronized void hacerCallback() throws java.rmi.RemoteException {
        System.out.println("Callback iniciado");
        ArrayList<Usuario> amigos = new ArrayList<>();
        int i = 0;
        for (String usuarioActual : listaClientes.keySet()) {
            i++;
            System.out.println("Haciendo el callback numero: " + i);

            for (String nombreAmigo : listaClientes.get(usuarioActual).getAmigos()) {
                if (listaClientes.containsKey(nombreAmigo.trim())) {
                    amigos.add(listaClientes.get(nombreAmigo.trim()));
                }
            }
            ClienteP2PInterfaz siguienteCliente = (ClienteP2PInterfaz) listaClientes.get(usuarioActual.trim()).getUsuario();
            siguienteCliente.notificarCliente((ArrayList<Usuario>) amigos.clone(), listaClientes.get(usuarioActual).getNombre().trim());

            amigos.clear();
        }
        System.out.println("Callback Finalizado");
    }

    /**
     * Añade usuarios para el registro callback con el objetivo de que reciban
     * los callback pertinentes con sus listas de amigos actualizadas conforme
     * los usuarios que sean amigos se conecten
     * @param usuario
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean iniciarSesionCallback(Usuario usuario) throws java.rmi.RemoteException {
        boolean nuevoInicio = true;
        for (Usuario us : listaClientes.values()) {
            if (us.getNombre().trim().equals(usuario.getNombre().trim())) {
                nuevoInicio = false;
                JOptionPane.showMessageDialog(null, "Sesion Ya Iniciada.\n", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
        if (nuevoInicio) {
            listaClientes.put(usuario.getNombre(), usuario);
            hacerCallback();
        }
        return nuevoInicio;
    }

    /**
     * Actualiza la lista de amigos de un usuario para tener en cuenta la nueva
     * relación de amistad entre el usuario y otro usuario
     * @param usuario
     * @param amigo
     * @throws RemoteException
     */
    @Override
    public void nuevoAmigo(String usuario, String amigo) throws java.rmi.RemoteException {
        ArrayList<Usuario> amigos = new ArrayList<>();

        listaClientes.get(usuario).setAmigos(amigo);

        for (String nombreAmigo : listaClientes.get(usuario).getAmigos()) {
            if (listaClientes.containsKey(nombreAmigo)) {
                amigos.add(listaClientes.get(nombreAmigo));
            }
        }
        ClienteP2PInterfaz siguienteCliente = (ClienteP2PInterfaz) listaClientes.get(usuario).getUsuario();
        siguienteCliente.notificarCliente(amigos, listaClientes.get(usuario).getNombre());
    }

}
