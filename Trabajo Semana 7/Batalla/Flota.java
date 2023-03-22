import java.util.*;
/**
 * Write a description of class Flota here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flota
{
    private String name;
    private Tablero tablero;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Marino> marinos;
    /**
     * Metodo constructor de la clase Flota
     */
    public Flota(){
        maquinas = new ArrayList<Maquina>();
        marinos = new ArrayList<Marino>();
    }
    
    public void buildMaquina(Maquina newMaquina){
        if((newMaquina instanceof Barco)  || (newMaquina instanceof PortaAviones))maquinas.add(newMaquina);
    }
    
    public void anadirAvion(int numeroPortaAvion, int longitud, int latitud, String placa, boolean enAire, Marino piloto){
        PortaAviones portaAvion;
        for(Maquina maquina : maquinas){
            if(maquina instanceof PortaAviones){
                portaAvion = (PortaAviones) maquina;
                if(portaAvion.getNumero() == numeroPortaAvion){
                    if(enAire){
                        portaAvion.anadirAvion(new Avion(longitud, latitud, placa, enAire, piloto));
                        maquinas.add(new Avion(longitud, latitud, placa, enAire, piloto));
                    } else{
                        portaAvion.anadirAvion(new Avion(portaAvion.getUbicacion()[0],portaAvion.getUbicacion()[1], placa, enAire, piloto));
                        maquinas.add(new Avion(portaAvion.getUbicacion()[0],portaAvion.getUbicacion()[1], placa, enAire, piloto));
                    }
                }
                
            }
        }
        
    }
    
    /**
     * Mueve la flota una posicion al norte
     */
    public void alNorte(){
        for(Maquina maquina : maquinas){
            maquina.setUbicacion(new Ubicacion(maquina.getUbicacion()[0] + 1, maquina.getUbicacion()[1]));
        }
    }
    
    /**
     * Mueve todas las maquinas la distancia definida
     * @param dLon, avance en longitud.
     * @param dLat, avance en latitud.
     */
    public void avance(int dLon, int dLat){
        for(Maquina maquina : maquinas){
            maquina.setUbicacion(new Ubicacion(maquina.getUbicacion()[0] + dLon, maquina.getUbicacion()[1] + dLat));
        }
    }
    
    /**
     * Consulta las maquinas debiles de una flota
     * Un barco es debil si tiene menos de 5 marinos; un avión, si no tiene piloto principal; y un portaaviones, si es un barco debil, o uno de 
     * sus aviones en el aire es debil.
     * return ArrayList de las maquinas debiles
     */
    public ArrayList<Maquina> maquinasDebiles(){
        ArrayList<Maquina> maquinasDebiles = new ArrayList<Maquina>();
        for(Maquina maquina : maquinas){
            if (maquina.esDebil()) maquinasDebiles.add(maquina);
        }
        
        
        return maquinasDebiles;
    }
    
    /**
     * Mueve todas las maquinas que no son débiles paso a paso (uno a uno) hacia la posición a atacar indicada por (lon, lat)
     * @param, lon, longitud
     * @param lat, latitud
     */
    
    public void ataquen(int lon, int lat){
        for(Maquina maquina : maquinas){
            if (!maquinasDebiles().contains(maquina)) maquina.setUbicacion(new Ubicacion(lon, lat));
        }
    }
}