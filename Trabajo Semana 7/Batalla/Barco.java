import java.util.*;
/**
 * Write a description of class Barco here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barco extends Maquina 
{
    private int numero;
    protected ArrayList<Marino> marinos;
    private ArrayList<Submarino> submarinos;
    /**
     * Metodo consturctor de barco
     */
   public Barco(int longitud, int latitud, int numero, Marino marino){
       super(longitud,latitud);
       this.numero = numero;
       marinos = new ArrayList<Marino>();
       marinos.add(marino);
       submarinos = new ArrayList<Submarino>();
   }
   
   /**
    * Funcion que determina si el barco es debil. Esto sucede si tiene menos de 5 marinos
    */
   
   public boolean esDebil(){
       return marinos.size() < 5;
   }
   
   public int getNumero(){
        return numero;
    }
    
    /**
     * Metodo que añade un submarino al barco
     */
    public void anadirSubmarino(Submarino submarino){
        submarinos.add(submarino);
    }
    
    /**
     * Función que indica los marinos pertenecientes al barco
     * @return ArryList de clase Marino
     */
    public ArrayList<Marino> getMarinos(){
        return marinos;
    }
    
    /**
     * Función que indica los submarinos cuya maquina nodriza es el barco
     * @return ArryList de clase Submarino
     */
    public ArrayList<Submarino> getSubmarinos(){
        return submarinos;
    }
    
    /**
     * Se sobreescribe el metodo equals
     */
    
    public boolean equals (Object objeto){
        boolean isEqual;
        try{
            Barco b = (Barco) objeto;
            if (numero == b.getNumero()){
                isEqual = true;
            }
            else{
                isEqual = false;
            }
           
        }
        catch (ClassCastException o){
            isEqual = false; 
        }
        
        return isEqual;
    }
    public void anadirMarino(Marino marino){
        marinos.add(marino);
    }
    
}

