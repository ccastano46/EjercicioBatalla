
/**
 * Write a description of class BatallaNavalException here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BatallaNavalException extends Exception
{
    public static final String FUERADELIMITE = "La cordenada excede los limites del tablero";
    public static final String PROBLEMASDEPOTENCIA = "La flota tiene menos marinos que maquinas";
    public static final String TABLEROSINPOTENCIA = "Más de la mitad de las flotas tienen problemas de potencias";
    public BatallaNavalException(String message){
        super(message);
    }
}
