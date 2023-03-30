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
     * @param flota, nombre de la flota a ser añadida
     */
    
    public void anadirFlota(String nombre){
        flotas.add(new Flota(nombre));
    }
    /**
    * Metodo para construir una maquina dentro de la flota dada
    * @param nombre, nombre de la flota a la cual se le va a añadir la maquina.
    * @param maquina, maquina a ser creada
    */
    public void buildMaquina(String nombre, Maquina maquina){
        for(Flota flota : flotas){
            if(nombre.equals(flota.getNombre())){
                flota.buildMaquina(maquina);
                break;
            }
        }
    }
    
    /**
     * Metodo para añadir avion a una flota.
     * @param nombre, nombre de la flota a la cual se le va a añadir la maquina.
     * @param avion, avion a ser creado.
     * @param numPortaAviones, numero del portavion al cual se le va a atribuir el avión.
     */
    
    public void anadirAvion(String nombre, Avion avion, int numPortaAviones){
        for(Flota flota : flotas){
            if(nombre.equals(flota.getNombre())){
                flota.anadirAvion(numPortaAviones, avion);
                break;
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
     * Consulta las flotas que tienen pilotos infiltrados
     * @trhows BatallaNavalException si la flota no tiene marinos.
     * @return contador, numero de infiltrados.
     */
    public int infiltrados()throws BatallaNavalException{
        int contador = 0;
        for(Flota flota : flotas){
            try{
                if(flota.piloto()==null) throw new BatallaNavalException(BatallaNavalException.FLOTAMARINONULL);
            }catch(BatallaNavalException flot){
                contador++;
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
    
    /**
     * Metodo que añade un marino a una flota indicada
     * @param nombre, nombre de la flota.
     * @param marino, marino a ser añadido a la flota.
     * @param maquina, maquina de la cual el marino va a ser parte.
     */
    public void anadirMarino(String nombre, Marino marino, Maquina maquina){
        for(Flota flota : flotas){
            if(nombre.equals(flota.getNombre())){
                flota.anadirMarino(marino, maquina);
                break;
            }
        }
    }
}
