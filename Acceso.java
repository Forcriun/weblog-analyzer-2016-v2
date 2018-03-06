import java.util.Arrays;

/**
 * Clase que representa accesos a un servidor web con fecha (yyyy-mm-dd) y hora(hh-mm).
 *
 * @author Didac Fernandez Fernandez
 * @version 2018-03-06
 */
public class Acceso
{
    // La direccion ip del cliente que realiza el acceso
    private String ip;
    
    // Los datos de la fecha del acceso
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    
    // La direccion web a la que accede el cliente
    private String url;
    
    // El codigo de respuesta HTTP del servidor
    private int Http;

    /**
     * Constructor para objetos de la clase Acceso. Recibe un String con los
     * datos del acceso y los almacena en los atributos de la clase.
     * 
     * @param  datos  Los datos del acceso en formato "91.244.73.61 [2016 01 01 10 56] instituto/normativa.html 403" 
     */
    public Acceso(String datos)
    {
        String[] datosAcceso = datos.split(" ");

        if(datos.matches(".*\\[.*\\].*")){
            this.ip = datosAcceso[0];
            this.ano = Integer.parseInt(datosAcceso[1].substring(1,4));
            this.mes = Integer.parseInt(datosAcceso[2]);
            this.dia = Integer.parseInt(datosAcceso[3]);
            this.hora = Integer.parseInt(datosAcceso[4]);
            this.minutos = Integer.parseInt(datosAcceso[5].substring(0,1));
            this.url = datosAcceso[6];
            this.Http = Integer.parseInt(datosAcceso[7]);
        }
        else{
            this.ano = Integer.parseInt(datosAcceso[0]);
            this.mes = Integer.parseInt(datosAcceso[1]);
            this.dia = Integer.parseInt(datosAcceso[2]);
            this.hora = Integer.parseInt(datosAcceso[3]);
            this.minutos = Integer.parseInt(datosAcceso[4]);
        }
    }

    /**
     * @return    La ip del cliente que realiza el acceso
     */
    public String getIp() 
    {
        return ip;
    }

    /**
     * @return    El ano del acceso
     */
    public int getAno() 
    {
        return ano;
    }

    /**
     * @return    El mes del acceso
     */
    public int getMes()
    {
        return mes;
    }

    /**
     * @return    El dia del acceso
     */
    public int getDia()
    {
        return dia;
    }

    /**
     * @return    La hora del acceso
     */
    public int getHora()
    {
        return hora;
    }

    /**
     * @return    Los minutos del acceso
     */
    public int getMinutos()
    {
        return minutos;
    }

    /**
     * @return    La url a la que se accede
     */
    public String getUrl() 
    {
        return url;
    }

    /**
     * @return    El codigo HTTP de respuesta del servidor
     */
    public int getHttp()
    {
        return Http;
    }
}