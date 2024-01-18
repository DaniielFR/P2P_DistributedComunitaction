package Cliente;
import Base.*;
import Servidor.ServidorP2PInterfaz;
import java.io.*;
import java.rmi.*;

/**
 * Clase que representa un Cliente para un objeto distribuido 
 * de la clase ServidorP2PImpl, que implementa la interfaz ServidorP2PInterfaz
 * Aceptando además callbacks por parte del servidor.
 * @author Marcos Garcia y Daniel Fuentes
 */

public class ClienteP2P {
    
    public static void main(String args[]) {
        try{
            String puertoRMI, registryURL;
            String nombreHost;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);

            System.out.println("Introduzca el nombre del Host del RMIRegistry: ");
            nombreHost = br.readLine();

            System.out.println("Introduzca el puerto del RMIRegistry: ");
            puertoRMI = br.readLine();

            registryURL = "rmi://"+ nombreHost +":"+ puertoRMI + "/callback";

            ServidorP2PInterfaz h = (ServidorP2PInterfaz)Naming.lookup(registryURL);
            System.out.println("Lookup completado");
            
            FachadaBase FachadaBase = new FachadaBase();
            
            VInicio vi = new VInicio(h,FachadaBase);
            vi.setVisible(true);
            
        }catch(Exception e){
            System.out.println("Excepción en ClienteCallback: "+e);
        }
    }
   
}