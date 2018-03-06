import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Metodo que devuelve la pagina web mas solicitada por los clientes. 
     * En caso de empate devuelve cualquiera de las paginas mas solicitadas.
     *
     * @return    Una cadena con la direccion web mas solicitada. Si no hay datos en el registro devuelve null.
     */
    public String paginaWebMasSolicitada() 
    {
        String aDevolver = null;

        // Crea un HashMap para organizar las visitas a las webs
        HashMap<String,Integer> mapaWebs = new HashMap<>();

        if(!accesos.isEmpty()){
            for(Acceso accesoActual : accesos){
                // Si la web a la que accede el cliente ya existe en el mapa actualiza el numero de accesos
                if(mapaWebs.containsKey(accesoActual.getUrl())){
                    mapaWebs.replace(accesoActual.getUrl(),mapaWebs.get(accesoActual.getUrl()) + 1);
                }
                // En caso contrario crea una nueva entrada para esa direccion web
                else{
                    mapaWebs.put(accesoActual.getUrl(),1);
                }
            }

            // Una vez organizados los datos busca la web mas solicitada            
            int maximoVisitas = 0;

            for(String url : mapaWebs.keySet()){
                if(mapaWebs.get(url) >= maximoVisitas){
                    maximoVisitas = mapaWebs.get(url);
                    aDevolver = url;
                }  
            }
        }
        else{
            System.out.println("No existen datos de acceso al servidor");
        }

        return aDevolver;
    }

    /**
     * Metodo que devuelve la pagina web mas solicitada por los clientes. 
     * En caso de empate devuelve la IP mas alta.
     *
     * @return    Una cadena con la direccion IP del cliente. Si no hay datos en el registro devuelve null.
     */
    public String clienteConMasAccesosExitosos()
    {
        String aDevolver = null;

        // Crea un HashMap para organizar los accesos exitosos
        HashMap<String,Integer> mapaAccesosExitosos = new HashMap<>();

        if(!accesos.isEmpty()){
            for(Acceso accesoActual : accesos){
                if(accesoActual.getHttp() == 200){
                    // Si la IP del cliente ya existe en el mapa actualiza el numero de accesos
                    if(mapaAccesosExitosos.containsKey(accesoActual.getIp())){
                        mapaAccesosExitosos.replace(accesoActual.getIp(),mapaAccesosExitosos.get(accesoActual.getIp()) + 1);
                    }
                    // En caso contrario crea una nueva entrada para esa IP
                    else{
                        mapaAccesosExitosos.put(accesoActual.getIp(),1);
                    }
                }
            }

            // Una vez organizados los datos busca la Ip con mas accesos exitosos
            int maximoAccesosExitosos = 0;            
            aDevolver = "192.168.1.0";  // Asigna un valor limite para poder comparar IPs con un valor no nulo

            for(String Ip : mapaAccesosExitosos.keySet()){
                if((Ip.compareTo(aDevolver) > 0 && mapaAccesosExitosos.get(Ip) >= maximoAccesosExitosos) || mapaAccesosExitosos.get(Ip) > maximoAccesosExitosos){
                    maximoAccesosExitosos = mapaAccesosExitosos.get(Ip);
                    aDevolver = Ip;
                }
            }
        }
        else{
            System.out.println("No existen datos de acceso al servidor");
        }

        return aDevolver;
    }

}
