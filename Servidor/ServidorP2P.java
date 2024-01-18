package Servidor;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.*;

/**
 * Clase Servidor que representa el servidor de objetos utilizando como
 * objeto remoto a exportar los objetos de la clase ServidorP2PImpl, permitiendo
 * además realizar callbacks al cliente.
 * @author Marcos Garcia y Daniel Fuentes
 */

public class ServidorP2P {

    public static void main(String args[]) {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String puerto, URL;
        try{
            System.out.print("Introduce el puerto para el registro RMI: ");
            puerto = (br.readLine()).trim();
            int puertoRMI = Integer.parseInt(puerto);
            iniciarRegistro(puertoRMI);

            ServidorP2PImpl objetoExportado = new ServidorP2PImpl();
            URL = "rmi://localhost:" + puertoRMI + "/callback";
            Naming.rebind(URL, objetoExportado);
            System.out.println("Servidor iniciado.");
        }
        catch (Exception re) {
            System.out.println("Excepcion iniciando el servidor: " + re);
        }
    }
    private static void iniciarRegistro(int puertoRMI) throws RemoteException{
        try {
            Registry registro = LocateRegistry.getRegistry(puertoRMI);
            registro.list();
        }
        catch (RemoteException e) {
            //No hay registro, en el puerto y se crea
            Registry registro = LocateRegistry.createRegistry(puertoRMI);
        }
    }

}
