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
    
    public Avion(int longitud, int latitud, String placa, boolean enAire){
        super(longitud, latitud);
        this.placa = placa;
        this.enAire = enAire;
    }
    
    /**
     * Metodo para asignarle al avión un piloto.
     */
    public void setPiloto(Marino piloto){
        this.piloto = piloto;
    }
    
    /**
    * Funcion que determina si el avión es debil. Esto sucede si no tiene piloto
    */
   
   public boolean esDebil(){
       return Objects.isNull(piloto);
   }
   
   /**
    * Función para identificar si el avión esta en el aire
    */
   
   public boolean isInAir(){
       return enAire;
   }
   
   /**
     * Función que indica los marinos pertenecientes al barco
     * @return ArryList de clase Marino
     */
    public ArrayList<Marino> getMarinos(){
        ArrayList<Marino> marinos = new ArrayList<Marino>();
        if(piloto != null) marinos.add(piloto);
        if(copiloto != null) marinos.add(copiloto);
        return marinos;
    }
    
}
