import java.util.*;
/**
 * Write a description of class PortaAviones here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PortaAviones extends Barco
{
    private int capacidad;
    private ArrayList<Avion> aviones;
    /**
     * Metodo constructor de PortaAviones
     */
    public PortaAviones(int longitud, int latitud, int numero, Marino marino, int capacidad){
        super(longitud, latitud, numero, marino);
        this.capacidad = capacidad;
        aviones = new ArrayList<Avion>();
    }
    
    /**
    * Funcion que determina si el barco es debil. Esto sucede si es un barco debil o si un avion que esta en el aire es debil
    */
   public boolean esDebil(){
       for(Avion avion : aviones){
           if(avion.esDebil() && avion.isInAir()) return true;
       }
       return super.esDebil();
    }
    
    /**
     * Añade un avion al porta aviones.
     */
    public void anadirAvion(Avion avion){
        aviones.add(avion);
    }
    
    /**
     * Metodo de get del ArrayList de aviones.
     */
    public ArrayList<Avion> getAviones(){
        return aviones;
    }
    

}
