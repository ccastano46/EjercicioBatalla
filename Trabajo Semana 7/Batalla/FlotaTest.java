

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class FlotaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FlotaTest
{
    /**
     * Metodo de prueba para verificar que todas las maquinas se muevan una posición al norte, siempre y cuando la nueva longitud no sea mayor a 180
     */
    @Test
    public void shouldMoveNorth(){
        Flota flota1 = new Flota();
        flota1.buildMaquina(new Barco(180, 70,1, new Marino("Camilo Castaño", 3)));
        flota1.buildMaquina(new Barco(1, 5,2, new Marino("Juan salazar", 1)));
    }
}
