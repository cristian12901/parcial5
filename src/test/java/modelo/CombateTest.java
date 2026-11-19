/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */


public class CombateTest {

    private Combate combate;
    private Jugador yugi;
    private Jugador kaiba;
    private Monstruo mago;
    private Monstruo dragon;

    @BeforeEach
    public void setUp() {
        combate = new Combate();
        yugi = new Jugador("Yugi");
        kaiba = new Jugador("Kaiba");
        mago = new Monstruo("Mago Oscuro", 2500, 2100, Posicion.ATAQUE);
        dragon = new Monstruo("Dragón Blanco", 3000, 2500, Posicion.ATAQUE);
        
        yugi.invocar(mago);
        kaiba.invocar(dragon);
    }

    @Test
    public void testAtacanteMasFuerte() {
        combate.atacar(kaiba, dragon, yugi, mago);
        
        assertEquals(7500, yugi.getLp());
        assertEquals(8000, kaiba.getLp());
        assertFalse(yugi.tieneMonstruos());
        assertTrue(kaiba.tieneMonstruos());
    }

    @Test
    public void testAtacanteMasDebil() {
        combate.atacar(yugi, mago, kaiba, dragon);
        
        assertEquals(7500, yugi.getLp());
        assertEquals(8000, kaiba.getLp());
        assertFalse(yugi.tieneMonstruos());
        assertTrue(kaiba.tieneMonstruos());
    }

    @Test
    public void testAtaqueDirectoSinDefensores() {
        kaiba.retirar(dragon);
        combate.atacarDirecto(yugi, mago, kaiba);
        
        assertEquals(5500, kaiba.getLp());
    }

    @Test
    public void testAtaqueDirectoConDefensores() {
        assertThrows(IllegalStateException.class, () -> {
            combate.atacarDirecto(yugi, mago, kaiba);
        });
    }

    @Test
    public void testAtacarConMonstruoAjeno() {
        assertThrows(IllegalArgumentException.class, () -> {
            combate.atacar(yugi, dragon, kaiba, mago);
        });
    }
}
