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
    private ArrayList<Marino> marinos;
    /**
     * Metodo consturctor de barco
     */
   public Barco(int longitud, int latitud, int numero, Marino marino){
       super(longitud,latitud);
       this.numero = numero;
       marinos = new ArrayList<Marino>();
       marinos.add(marino);
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
}
