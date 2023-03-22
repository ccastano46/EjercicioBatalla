import java.util.*;
/**
 * Write a description of class Avion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Avion extends Maquina
{
    private String placa;
    private boolean enAire;
    private Marino piloto;
    private Marino copiloto;
    
    /**
     * Constructor avion
     */
    
    public Avion(int longitud, int latitud, String placa, boolean enAire, Marino piloto){
        super(longitud, latitud);
        this.placa = placa;
        this.enAire = enAire;
        this.piloto = piloto;
    }
    
    /**
    * Funcion que determina si el avión es debil. Esto sucede si no tiene piloto
    */
   
   public boolean esDebil(){
       return Objects.isNull(piloto);
   }
    
}
