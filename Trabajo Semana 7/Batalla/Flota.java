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
    public Flota(String nombre){
        this.nombre = nombre;
        maquinas = new ArrayList<Maquina>();
        marinos = new ArrayList<Marino>();
        marinosDestruidos = new ArrayList<Marino>();
        barcosDestruidos = new ArrayList<Barco>();
        submarinosDestruidos = new ArrayList<Submarino>();
    }
    /**
     * Función que indica el nombre de la flota
     * @return nombre
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Metodo que añade una maquina a la flota
     */
    public void buildMaquina(Maquina newMaquina){
        if((newMaquina instanceof Barco))maquinas.add(newMaquina);
        else if(newMaquina instanceof Submarino) anadirSubmarino((Submarino)newMaquina);
        else JOptionPane.showMessageDialog(null, "No puede crear un avión sin antes crearlo en el porta aviones");
        marinos.addAll(newMaquina.getMarinos());
    }
    /**
     * Metodo que crea un avión en la flota y se lo asigna a un porta avión previamente creado.
     */
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
        if(!encontrado) JOptionPane.showMessageDialog(null, "No se encontro el portaaviones");
    }
    
    /**
     * Metodo que añade un submarino a una flota y a su maquina nodriza
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
                    buildMaquina(submarino.getNodriza());
                    ((Barco) maquinas.get(maquinas.size()-1)).anadirSubmarino(submarino);
                }
                maquinas.add(submarino);
                //Se analiza la opción de que nodriza es un submarino.
            }else if(submarino.getNodriza() instanceof Submarino){ 
                //Se trata de ver si la nodriza ya esta registrada en las maquinas.
                for(Maquina maquina : maquinas){
                   try{
                       if(((Submarino) submarino.getNodriza()).getNumero() == ((Submarino) maquina).getNumero()){
                       ((Submarino) maquina).anadirSubmarino(submarino);
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
                    buildMaquina(submarino.getNodriza());
                    ((Submarino) maquinas.get(maquinas.size()-1)).anadirSubmarino(submarino);
                }
                maquinas.add(submarino);
            }
        }catch(NullPointerException dispositivo){
            JOptionPane.showMessageDialog(null, "La maquina nodriza no puede ser nula");
        }
    }
        
    /**
     * Mueve la flota una posicion al norte
     * @throw BatallaNavalException si una de sus maquinas no logra realizar la operación
     */
    public void alNorte() throws BatallaNavalException{
        for(Maquina maquina : maquinas){
            if(maquina.getUbicacion()[1] + 1 > 90) throw new BatallaNavalException(BatallaNavalException.FUERADELIMITE);
            maquina.setUbicacion(new Ubicacion(maquina.getUbicacion()[0], maquina.getUbicacion()[1] + 1));
        }
    }
    
    /**
     * Mueve todas las maquinas la distancia definida
     * @param dLon, avance en longitud.
     * @param dLat, avance en latitud.
     */
    public void avance(int dLon, int dLat) throws BatallaNavalException{
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
                    encontrado = true;
                    for(Submarino submarino : ((Barco) maquina).getSubmarinos()){
                        autoDestruirSubmarino(submarino);
                    }
                    JOptionPane.showMessageDialog(null, "El barco numero " + numeroBarco + " ha sido destruido por solicitud de la flota y junto a el, todos sus submarinos al no tener maquina nodriza");
                }
            }
            if(encontrado) break;
        }
        if(!encontrado) JOptionPane.showMessageDialog(null, "Barco no existente dentro de esta flota");
    }
    
    /**
     * Metodo para autodestruir un marino
     */
    public void autoDestruirMarino(Marino marino){
        boolean encontrado = false;
        for(Marino tripulante : marinos){
            if(tripulante.equals(marino)){
                encontrado = true;
                marinos.remove(tripulante);
                marinos.add(tripulante);
            }
            if(encontrado){
                JOptionPane.showMessageDialog(null, "El marino a sido destruido por solicitud");
                break; 
            }
        }
        if(!encontrado) JOptionPane.showMessageDialog(null, "El marino no existe");
            
    }
    
    /**
     * Función que autodestruye todos los submarinos, una vez se destruye su madre nodriza
     * @param submarino, submarino a destruir
     */
    
    private void autoDestruirSubmarino(Submarino submarino){
        boolean encontrado;
        for(Maquina maquina : maquinas){
            try{
                if(submarino.getNumero() == ((Submarino) maquina).getNumero()){
                    encontrado = true;
                    submarinosDestruidos.add((Submarino) maquina);
                    maquinas.remove(maquina);
                    for(Submarino subma : ((Submarino) maquina).getSubmarinos()){
                        autoDestruirSubmarino(subma);
                    }
                }else encontrado = false;
            }catch(ClassCastException dispo){
                encontrado = false;
            }
            if(encontrado) break;
        }
    }
    /**
     * Consulta la potencia de la flota. 
     * @throws BatallaNaval si existen menos marinos que máquinas en la flota
     */
    
    public int potencia() throws BatallaNavalException{
        if(marinos.size() < maquinas.size()) throw new BatallaNavalException(BatallaNavalException.PROBLEMASDEPOTENCIA);
        return maquinas.size() - maquinasDebiles().size();
    }
    
    /**
     * Metodo que enlista un marino en la maquina asignada
     * @param marino, marino a ser añadido a la flota.
     * @param maquina, maquina de la cual el marino va a ser parte.
     */
    public void anadirMarino(Marino marino, Maquina maquina){
        for(Maquina maquina1 : maquinas){
            if(!(maquina1 instanceof Submarino) && maquina1.equals(maquina)){
                maquina1.anadirMarino(marino);
                marinos.add(marino);
                break;
            }
        }
    }
    
    /**
     * Consulta los pilotos de la flota.
     * @trhows BatallaNavalException si el  piloto no es un marino de portaviones.
     * @trhows BatallaNavalException si el piloto se pilotea otro avion diferente.
     * @return ArrayList de marino pilotos
     */
    public ArrayList<Marino> piloto() throws BatallaNavalException{
        ArrayList<Marino> avionesPilotos = new ArrayList<Marino>();
        ArrayList<Marino> portaAvionesMarinos = new ArrayList<Marino>();
        for(Maquina maquina : maquinas){
            if(maquina instanceof Avion){
                Avion avion = (Avion) maquina;
                avionesPilotos.add(avion.getMarinos().get(0));
            }
            if(maquina instanceof PortaAviones){
                PortaAviones port = (PortaAviones) maquina;
                avionesPilotos.addAll(port.getMarinos());
            }
        }
        if (foundDuplicate(avionesPilotos)) throw new BatallaNavalException(BatallaNavalException.PILOTOINFILTRADO);
        if(!portaAvionesMarinos.containsAll(avionesPilotos)) throw new BatallaNavalException(BatallaNavalException.PILOTOINFILTRADO);
        return avionesPilotos;
    }
    
    private boolean foundDuplicate(ArrayList<Marino> list){
        boolean foundDuplicate = false;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    foundDuplicate = true;
                    break;
                }
            }
            if (foundDuplicate) {
                break;
            }
        }
        return foundDuplicate;
    }
    
    public int numMaquinas(){
        return maquinas.size();
    }
    public int numMarinos(){
        return marinos.size();
    }
    
}
