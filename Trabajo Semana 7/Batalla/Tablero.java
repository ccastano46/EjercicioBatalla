import java.util.*;
/**
 * Write a description of class Tablero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tablero
{
    private ArrayList<Flota> flotas;
    
    /**
     * Metodo constructor de la clase Tablero
     */
    public Tablero(){
        flotas = new ArrayList<Flota>();
    }
    
    /**
     * Metodo para crear una flota dentro del tablero
     * @param flota, flota a ser añadida
     */
    
    public void anadirFlota(String nombre){
        flotas.add(new Flota(nombre));
    }
    
    public void buildMaquina(String nombre, Maquina maquina){
        for(Flota flota : flotas){
            if(nombre.equals(flota.getNombre())){
                flota.buildMaquina(maquina);
            }
        }
    }
    
    /**
     * Consulta el número de flotas que lograron un movimiento completo
     * @throws BatallaNavalException si más de la mitad de las flotas tienen problemas de potencia
     */
    public int alNorte(){
        int contador = flotas.size();
        for(Flota flota : flotas){
            try{
                flota.alNorte();
            }catch(BatallaNavalException flot){
                contador--;
            }
        }
        return contador;
    }
    
    /**
     * Consulta la potencia del tablero
     * @trhows BatallaNavalException si más de la mitad de las flotas tienen problemas de potencia
     */
    public int potencia() throws BatallaNavalException{
        int potencia = 0;
        int contador = 0;
        for(Flota flota: flotas){
            try{
                potencia+= flota.potencia();
            }catch(BatallaNavalException dañada){
                contador++;
            }
        }
        if(contador > (int) flotas.size() / 2) throw new BatallaNavalException(BatallaNavalException.TABLEROSINPOTENCIA);
        return potencia;
    }
    
    /**
     * Consulta el numero de maquinas que existen en el tablero
     */
    public int numMaquinas(){
        int contador = 0;
        for(Flota flota : flotas){
            contador += flota.numMaquinas();
        }
        return contador;
    }
    
    /**
     * Consulta el numero de marinos que existen en el tablero
     */
    public int numMarinos(){
        int contador = 0;
        for(Flota flota : flotas){
            contador += flota.numMarinos();
        }
        return contador;
    }
}
