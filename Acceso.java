import java.util.Arrays;

/**
 * Clase que representa accesos a un servidor web con fecha (yyyy-mm-dd) y hora(hh-mm).
 *
 * @author Didac Fernandez Fernandez
 * @version 2018-03-06
 */
public class Acceso
{
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minutos;
    
    /**
     * Constructor para objetos de la clase Acceso. Recibe un String con los
     * datos del acceso y los almacena en los atributos de la clase.
     * 
     * @param  datos  La fecha del acceso en formato "yyyy mm dd hh mm" 
     */
    public Acceso(String datos)
    {
        String[] datosAcceso = datos.split(" ");
        
        this.ano = Integer.parseInt(datosAcceso[0]);
        this.mes = Integer.parseInt(datosAcceso[1]);
        this.dia = Integer.parseInt(datosAcceso[2]);
        this.hora = Integer.parseInt(datosAcceso[3]);
        this.minutos = Integer.parseInt(datosAcceso[4]);
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
}