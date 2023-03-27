import javax.swing.JOptionPane;
import java.util.*;
import java.util.Arrays; 
/**
 * Write a description of class Submarino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Submarino extends Maquina
{
    private static int NUMERO;
    private long profundidad = 0;
    private  Barco maquinaNodriza;
    private Submarino maquinaNodriza1;
    private ArrayList<Submarino> submarinos;
    private int numero;
   public Submarino(int longitud, int latitud, Maquina maquinaNodriza){
       super(longitud,latitud);
       setNodriza(maquinaNodriza);
       submarinos = new ArrayList<Submarino>();
       NUMERO++;
       numero = NUMERO;
   }
   
   /**
    * Función que identifica si un submarino es debil.
    * @return false, un submarino NUNCA es debil
    */
   
   public final boolean esDebil(){
       return false;
   }
   
   /**
    * Metodo para asignarle al submarino una maquina nodriza
    */
   public void setNodriza(Maquina nodriza){
       if(nodriza instanceof Barco) maquinaNodriza = (Barco)nodriza;
       else if(nodriza instanceof Submarino) maquinaNodriza1 = (Submarino)nodriza;
       else JOptionPane.showMessageDialog(null, "La madre nodriza no puede ser diferente a un barco o submarino");
       
       
   }
   
   /**
    * Función que indica la maquina nodriza del submarino
    */
   
   public Maquina getNodriza() throws NullPointerException{
       if(maquinaNodriza != null) return maquinaNodriza;
       else if(maquinaNodriza1 != null) return maquinaNodriza1;
       else {
           throw new NullPointerException("La maquina nodriza no puede ser nula");
       }
       
   }
   
   /**
     * Metodo que añade un submarino al barco
     */
    public void anadirSubmarino(Submarino submarino){
        submarinos.add(submarino);
    }
    /**
     * Función que indica los marinos pertenecientes al barco
     * @return ArryList de clase Marino vacia, ya que los submarinos no tienen ningun marino abordo
     */
    public ArrayList<Marino> getMarinos(){
        return new ArrayList<Marino>();
    }
   public int getNumero(){
        return numero;
    }
    
    
}
