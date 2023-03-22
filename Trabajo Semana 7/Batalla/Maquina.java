
/**
 * Write a description of class Maquina here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Maquina
{
    private Ubicacion ubicacion;
    
    /**
     * constructor Maquina
     */
    public Maquina(int longitud, int latitud){
        ubicacion = new Ubicacion(longitud, latitud);
    }
    /**
     * metodo que le da una nueva ubicacion a la maquina.
     */
    public void setUbicacion(Ubicacion newUbicacion){
        ubicacion = newUbicacion;
    }
    /**
     * Funcion que retorna la longitud y latitud de la maquina
     */
    public int[] getUbicacion(){
        return new int[] {ubicacion.getLongitud(),ubicacion.getLatitud()};
    }
    /**
     * Función abstracta que debería indicar si una maquina es debil o no.
     */
    public abstract boolean esDebil();
    
    
    
}
