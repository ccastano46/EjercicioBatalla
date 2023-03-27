
/**
 * Write a description of class Marino here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Marino
{
    private String nombre;
    private int rango;
    
    /**
     * Constructor Marino
     */
    
    public Marino(String nombre, int rango){
        this.nombre = nombre;
        this.rango = rango;
    }
    
    /**
     * Función que indica el nombre del marino
     * @return, nombre
     */
    
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Función que indica el rango del marino
     */
    
    public int getRango(){
        return rango;
    }
    
    /**
     * Función que sobre escribe el metodo equals heredado de Object
     * @param objeto, objeto a ser comparado con el marino
     */
    
    public boolean equals(Object objeto){
        boolean isEquals;
        Marino marino = (Marino) objeto;
        try{
            if(nombre.equals(marino.getNombre()) &&  (rango == marino.getRango())) isEquals = true;
            else isEquals = false;
        }catch(ClassCastException mar){
            isEquals = false;
        }
        return isEquals;
    }
}
