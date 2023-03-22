
/**
 * Write a description of class Ubicacion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ubicacion
{
   private int longitud;
   private int latitud;
   
   /**
    * Metodo constructor de la clase Ubicacion
    */
   
   public Ubicacion(int longitud, int latitud){
       setLatitud(latitud);
       setLongitud(longitud);
   }
   /**
    * Metodo que ajusta la latitud de la ubicación, siempre y cuando esta este dentro del rango [-90,90]
    */
   public void setLatitud(int newLatitud){
       if(-90 <= newLatitud && newLatitud <= 90) latitud = newLatitud;
   }
   /**
    * Metodo que ajusta la latitud de la ubicación, siempre y cuando esta este dentro del rango [,180]
    */
   public void setLongitud(int newLongitud){
       if(0 <= newLongitud && newLongitud <= 180)longitud = newLongitud;
   }
   /**
    * Función que retorna la latitud de la ubicacion
    */
   public int getLatitud(){
       return latitud;
   }
   /**
    * Funcion que retorna la longitud de la ubicacion
    */
   public int getLongitud(){
       return longitud;
   }
}
