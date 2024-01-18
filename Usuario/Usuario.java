package Usuario;
import Cliente.*;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Objects;

public class Usuario implements Serializable{

    private ClienteP2PInterfaz usuario;
    private String nombre;
    private ArrayList<String> amigos;

    public Usuario(ClienteP2PInterfaz usuario, String nombre,ArrayList<String> amigos) {
        this.usuario = usuario;
        this.nombre = nombre;
         this.amigos = amigos;
    }

    public ClienteP2PInterfaz getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<String> getAmigos() {
        return amigos;
    }

    public void setAmigos(String nuevoAmigo) {
        this.amigos.add(nuevoAmigo);
    }
    

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.usuario);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
}
