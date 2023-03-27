import java.util.*;
import javax.swing.JOptionPane;
/**
 * Write a description of class Flota here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flota
{
    private String nombre;
    private Tablero tablero;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Marino> marinos;
    private ArrayList<Marino> marinosDestruidos;
    private ArrayList<Barco> barcosDestruidos;
    private ArrayList<Submarino> submarinosDestruidos;
    /**
     * Metodo constructor de la clase Flota
     */
    public Flota(){
        maquinas = new ArrayList<Maquina>();
        marinos = new ArrayList<Marino>();
        marinosDestruidos = new ArrayList<Marino>();
        barcosDestruidos = new ArrayList<Barco>();
        submarinosDestruidos = new ArrayList<Submarino>();
    }
    
    public void buildMaquina(Maquina newMaquina){
        if((newMaquina instanceof Barco)  || (newMaquina instanceof PortaAviones))maquinas.add(newMaquina);
        else if(newMaquina instanceof Submarino) anadirSubmarino((Submarino)newMaquina);
        else JOptionPane.showMessageDialog(null, "No puede crear un avión sin antes crearlo en el porta aviones");
    }
    
    public void anadirAvion(int numeroPortaAvion, Avion avion){
        PortaAviones portaAvion;
        boolean encontrado = false;
        for(Maquina maquina : maquinas){
            if(maquina instanceof PortaAviones){
                portaAvion = (PortaAviones) maquina;
                if(portaAvion.getNumero() == numeroPortaAvion){
                    encontrado = true;
                    if(avion.isInAir()){
                        maquinas.add(avion);
                        portaAvion.anadirAvion(avion);
                    } else{
                        avion.setUbicacion(new Ubicacion(portaAvion.getUbicacion()[0], portaAvion.getUbicacion()[1]));
                        maquinas.add(avion);
                        portaAvion.anadirAvion(avion);
                    }
                    
                }
                
            }
            if(encontrado) break;
        }
        
    }
    
    /**
     * Metodo que añade un submarino a una flota
     */
    
    private void anadirSubmarino(Submarino submarino){
        boolean encontrado = false;
        //Se analiza si la madre nodriza es barco.
        try{
            if(submarino.getNodriza() instanceof Barco){
                //Se trata de ver si la nodriza ya esta registrada en las maquinas.
                for(Maquina maquina : maquinas){
                   try{
                       if(((Barco)submarino.getNodriza()).getNumero() == ((Barco) maquina).getNumero()){
                       ((Barco) maquina).anadirSubmarino(submarino);
                       maquinas.add(submarino);
                       encontrado = true;
                       }
                   }catch(ClassCastException dispositivo){
                       //Maquina no es un barco 
                       encontrado = false;
                   }
                   if(encontrado) break;
                }
                //Si la nodriza no esta registrada, se registra
                if(!encontrado){
                    maquinas.add(submarino.getNodriza());
                    ((Barco) maquinas.get(maquinas.size()-1)).anadirSubmarino(submarino);
                }
                //Se analiza la opción de que nodriza es un submarino.
            }else if(submarino.getNodriza() instanceof Submarino){ 
                //Se trata de ver si la nodriza ya esta registrada en las maquinas.
                for(Maquina maquina : maquinas){
                   try{
                       if(((Submarino) submarino.getNodriza()).getNumero() == ((Submarino) maquina).getNumero()){
                       ((Submarino) maquina).anadirSubmarino(submarino);
                       maquinas.add(submarino);
                       encontrado = true;
                       }
                   }catch(ClassCastException dispositivo){
                       //Maquina no es un submarino
                       encontrado = false;
                   }
                   if(encontrado) break;
                }
                //Si la nodriza no esta registrada, se registra.
                if(!encontrado){
                    maquinas.add(submarino.getNodriza());
                    ((Submarino) maquinas.get(maquinas.size()-1)).anadirSubmarino(submarino);
                }
            }
        }catch(NullPointerException dispositivo){
            JOptionPane.showMessageDialog(null, "La maquina nodriza no puede ser nula");
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
    
    /**
     * Metodo para saber que marinos pertenecen a la tropa
     * @return ArrayList de la clase Marino
     */
    
    public ArrayList<Marino> getMarinos(){
        for(Maquina maquina : maquinas){
            marinos.addAll(maquina.getMarinos());
        }
        return marinos;
    }
    
    /**
     * Metodo para autodestruir un barco
     */
    
    public void autoDestruirBarco(int numeroBarco){
        boolean encontrado = false;
        for(Maquina maquina : maquinas){
            if(maquina instanceof Barco){
                if(numeroBarco == ((Barco) maquina).getNumero()){
                    barcosDestruidos.add((Barco) maquina);
                    maquinas.remove(maquinas.indexOf(maquina));
                    marinosDestruidos.addAll(maquina.getMarinos());
                    submarinosDestruidos.addAll(((Barco) maquina).getSubmarinos());
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "El barco numero " + numeroBarco + " ha sido destruido por solicitud de la flota y junto a el, todos sus submarinos y marinos");
                }
            }
            if(encontrado) break;
        }
        if(!encontrado) JOptionPane.showMessageDialog(null, "Barco no existente dentro de esta flota");
    }
}
