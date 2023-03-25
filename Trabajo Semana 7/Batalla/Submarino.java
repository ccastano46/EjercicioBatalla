import javax.swing.JOptionPane;
import java.util.*;
/**
 * Write a description of class Submarino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Submarino extends Maquina
{
    private long profundidad = 0;
    private  Barco maquinaNodriza;
    private Submarino maquinaNodriza1;
    private ArrayList<Submarino> submarinos;
   public Submarino(int longitud, int latitud, Object maquinaNodriza){
       super(longitud,latitud);
       setNodriza(maquinaNodriza);
       submarinos = new ArrayList<Submarino>();
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
   public void setNodriza(Object nodriza){
       if(nodriza instanceof Barco) maquinaNodriza = (Barco)nodriza;
       else if(nodriza instanceof Submarino) maquinaNodriza1 = (Submarino)nodriza;
       else JOptionPane.showMessageDialog(null, "La madre nodriza no puede ser diferente a un barco o submarino");
       
       
   }
}
