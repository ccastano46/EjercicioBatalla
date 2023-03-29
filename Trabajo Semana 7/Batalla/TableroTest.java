

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TableroTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TableroTest
{
    /**
     * Metodo de prueba para verificar que todas las maquinas se muevan una posición al norte, siempre y cuando la nueva longitud no sea mayor a 180
     */
    @Test
    public void shouldCreat(){
        Tablero tablero = new Tablero();
        tablero.anadirFlota("Flota1");
        tablero.anadirFlota("Flota2");
        tablero.buildMaquina("Flota1", new Submarino(50,50, new Barco(50, 50,1, new Marino("Camilo",1))));
        tablero.buildMaquina("Flota2", new Submarino(80,50, new Submarino(70,80, new Barco(70, 50,2, new Marino("Juan",1)))));
        assertEquals(tablero.numMaquinas(), 5);
        assertEquals(tablero.numMarinos(), 2);
    }
    /**
     * Metodo para verificar que se calcula las flotas que pudieron mover al norte completamente
     */
    
    @Test
    public void shouldAlNorte(){
        Tablero tablero = new Tablero();
        tablero.anadirFlota("Flota1");
        tablero.anadirFlota("Flota2");
        tablero.buildMaquina("Flota1", new Barco(180,90, 1, new Marino("Camilo",1)));
        tablero.buildMaquina("Flota2", new Barco(180,0, 1, new Marino("Camilo",1)));
        assertEquals(1,tablero.alNorte());
    }
    
    /**
     * Metodo para verificar que se lanze una excepción al momento de consultar la potencia del tablero
     */
    @Test()
    public void shouldFailPotencia() throws BatallaNavalException {
        Tablero tablero = new Tablero();
        tablero.anadirFlota("Flota1");
        tablero.anadirFlota("Flota2");  
        tablero.buildMaquina("Flota1", new Submarino(50,50, new Barco(50, 50,1, new Marino("Camilo",1))));
        tablero.buildMaquina("Flota2", new Submarino(80,50, new Submarino(70,80, new Barco(70, 50,2, new Marino("Juan",1)))));
        tablero.potencia();
    }
}
