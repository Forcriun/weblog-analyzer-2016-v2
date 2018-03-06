import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase que representa objetos capaces de leer, analizar y procesar datos de registros de acceso a
 * un servidor web.
 *
 * @author Didac Fernandez Fernandez
 * @version 2018-03-06
 */
public class AnalizadorAccesosAServidor
{
    private ArrayList<Acceso> accesos;
    
    
    /**
     * Constructor de objetos de la clase AnalizadorAccesosAServidor
     */
    public AnalizadorAccesosAServidor() 
    {
        accesos = new ArrayList<>();
    }
        
    /**
     * Metodo que lee y analiza un archivo de log indicado por parametro. El 
     * archivo debe estar almacenado en el directorio del proyecto.
     * 
     * @param log El nombre del archivo
     */
    public void analizarArchivoDeLog(String archivo)
    {
        accesos.clear();
        File archivoALeer = new File(archivo);
        try {
            Scanner sc = new Scanner(archivoALeer);
            while (sc.hasNextLine()) {
                // Pasa como argumento del constructor la siguiente entrada en el log             
                Acceso accesoActual = new Acceso(sc.nextLine());
                
                accesos.add(accesoActual);
            }
            sc.close();
        }
        catch (Exception e) {
            System.out.println("Ocurrio algun error al leer el archivo.");
        }
    }
    
    /**
     * Metodo que devuelve la hora a la que se producen mas accesos al
     * servidor. En caso de empate devuelve la hora mas alta.
     *
     * @return    La hora con mas accesos. Si no hay datos en el registro devuelve -1
     */
    public int obtenerHoraMasAccesos() 
    {
        int valorADevolver = -1;
        
        if (!accesos.isEmpty()) {
            int[] accesosPorHora = new int[24];
    
            for (Acceso accesoActual : accesos) {
                int horaAccesoActual = accesoActual.getHora();
                accesosPorHora[horaAccesoActual] = accesosPorHora[horaAccesoActual] + 1;
            }
            
            int numeroDeAccesosMasAlto = accesosPorHora[0];
            int horaDeAccesosMasAlto = 0;
            for (int i = 0; i < accesosPorHora.length; i++) {
                if (accesosPorHora[i] >= numeroDeAccesosMasAlto) {
                    numeroDeAccesosMasAlto = accesosPorHora[i];
                    horaDeAccesosMasAlto = i;
                }
            }
            
            valorADevolver = horaDeAccesosMasAlto;                      
        }
        
        return valorADevolver;
    }

    
    
    public String paginaWebMasSolicitada() 
    {
        return "";
    }
    
    public String clienteConMasAccesosExitosos()
    {
        return "";
    }


}
