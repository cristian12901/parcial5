/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */


public class JugadorTest {

    @Test
    public void testLpPorDefecto() {
        Jugador jugador = new Jugador("Yugi");
        assertEquals(8000, jugador.getLp());
    }

    @Test
    public void testLpInicialPersonalizado() {
        Jugador jugador = new Jugador("Kaiba", 4000);
        assertEquals(4000, jugador.getLp());
    }

    @Test
    public void testRecibirDanio() {
        Jugador jugador = new Jugador("Yugi");
        jugador.recibirDanio(1500);
        
        assertEquals(6500, jugador.getLp());
        assertFalse(jugador.estaDerrotado());
    }

    @Test
    public void testRecibirDanioNoBajaDeCero() {
        Jugador jugador = new Jugador("Yugi", 1000);
        jugador.recibirDanio(5000);
        
        assertEquals(0, jugador.getLp());
        assertTrue(jugador.estaDerrotado());
    }

    @Test
    public void testDanioNegativo() {
        Jugador jugador = new Jugador("Yugi");
        assertThrows(IllegalArgumentException.class, () -> {
            jugador.recibirDanio(-1);
        });
    }

    @Test
    public void testLpInicialNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugador("Yugi", -10);
        });
    }

    @Test
    public void testNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Jugador("  ");
        });
    }

    @Test
    public void testInvocarYRetirarMonstruos() {
        Jugador jugador = new Jugador("Yugi");
        Monstruo mago = new Monstruo("Mago Oscuro", 2500, 2100, Posicion.ATAQUE);
        
        assertFalse(jugador.tieneMonstruos());
        jugador.invocar(mago);
        assertTrue(jugador.tieneMonstruos());
        assertTrue(jugador.retirar(mago));
        assertFalse(jugador.tieneMonstruos());
    }
}

