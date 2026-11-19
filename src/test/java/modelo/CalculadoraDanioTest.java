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


public class CalculadoraDanioTest {

    @Test
    public void testAtaqueVsAtaqueGanaAtacante() {
        Monstruo dragon = new Monstruo("Dragón Blanco", 2500, 2100, Posicion.ATAQUE);
        Monstruo mago = new Monstruo("Mago Oscuro", 2000, 1700, Posicion.ATAQUE);

        ResultadoCombate resultado = CalculadoraDanio.calcular(dragon, mago);

        assertEquals(500, resultado.getDanio());
        assertEquals(Objetivo.DEFENSOR, resultado.getObjetivoDanio());
        assertTrue(resultado.isDefensorDestruido());
        assertFalse(resultado.isAtacanteDestruido());
    }

    @Test
    public void testAtaqueVsAtaquePierdeAtacante() {
        Monstruo mago = new Monstruo("Mago Oscuro", 2000, 1700, Posicion.ATAQUE);
        Monstruo dragon = new Monstruo("Dragón Blanco", 2500, 2100, Posicion.ATAQUE);

        ResultadoCombate resultado = CalculadoraDanio.calcular(mago, dragon);

        assertEquals(500, resultado.getDanio());
        assertEquals(Objetivo.ATACANTE, resultado.getObjetivoDanio());
        assertTrue(resultado.isAtacanteDestruido());
        assertFalse(resultado.isDefensorDestruido());
    }

    @Test
    public void testAtaqueVsAtaqueEmpate() {
        Monstruo atacante = new Monstruo("Guerrero 1", 1800, 1000, Posicion.ATAQUE);
        Monstruo defensor = new Monstruo("Guerrero 2", 1800, 1000, Posicion.ATAQUE);

        ResultadoCombate resultado = CalculadoraDanio.calcular(atacante, defensor);

        assertEquals(0, resultado.getDanio());
        assertEquals(Objetivo.NINGUNO, resultado.getObjetivoDanio());
        assertTrue(resultado.isAtacanteDestruido());
        assertTrue(resultado.isDefensorDestruido());
    }

    @Test
    public void testAtaqueContraDefensaMenor() {
        Monstruo atacante = new Monstruo("Caballero", 2000, 1200, Posicion.ATAQUE);
        Monstruo defensor = new Monstruo("Escudero", 500, 1500, Posicion.DEFENSA);

        ResultadoCombate resultado = CalculadoraDanio.calcular(atacante, defensor);

        assertEquals(0, resultado.getDanio());
        assertEquals(Objetivo.NINGUNO, resultado.getObjetivoDanio());
        assertTrue(resultado.isDefensorDestruido());
        assertFalse(resultado.isAtacanteDestruido());
    }

    @Test
    public void testAtaqueContraDefensaMayor() {
        Monstruo atacante = new Monstruo("Duende", 1000, 500, Posicion.ATAQUE);
        Monstruo defensor = new Monstruo("Golem", 500, 1800, Posicion.DEFENSA);

        ResultadoCombate resultado = CalculadoraDanio.calcular(atacante, defensor);

        assertEquals(800, resultado.getDanio());
        assertEquals(Objetivo.ATACANTE, resultado.getObjetivoDanio());
        assertFalse(resultado.isAtacanteDestruido());
        assertFalse(resultado.isDefensorDestruido());
    }

    @Test
    public void testAtaqueContraDefensaIgual() {
        Monstruo atacante = new Monstruo("Arquero", 1500, 1000, Posicion.ATAQUE);
        Monstruo defensor = new Monstruo("Muralla", 500, 1500, Posicion.DEFENSA);

        ResultadoCombate resultado = CalculadoraDanio.calcular(atacante, defensor);

        assertEquals(0, resultado.getDanio());
        assertEquals(Objetivo.NINGUNO, resultado.getObjetivoDanio());
        assertFalse(resultado.isAtacanteDestruido());
        assertFalse(resultado.isDefensorDestruido());
    }

    @Test
    public void testAtaqueDirecto() {
        Monstruo atacante = new Monstruo("Bestia", 1900, 1200, Posicion.ATAQUE);

        ResultadoCombate resultado = CalculadoraDanio.calcular(atacante);

        assertEquals(1900, resultado.getDanio());
        assertEquals(Objetivo.DEFENSOR, resultado.getObjetivoDanio());
    }

    @Test
    public void testMonstruoEnDefensaNoPuedeAtacar() {
        Monstruo monstruoEnDefensa = new Monstruo("Tortuga", 1000, 2000, Posicion.DEFENSA);
        Monstruo enemigo = new Monstruo("Lobo", 500, 500, Posicion.ATAQUE);

        assertThrows(IllegalStateException.class, () -> {
            CalculadoraDanio.calcular(monstruoEnDefensa, enemigo);
        });

        assertThrows(IllegalStateException.class, () -> {
            CalculadoraDanio.calcular(monstruoEnDefensa);
        });
    }

    @Test
    public void testParametrosNulos() {
        Monstruo monstruoValido = new Monstruo("Slime", 500, 500, Posicion.ATAQUE);

        assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraDanio.calcular(null, monstruoValido);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraDanio.calcular(monstruoValido, null);
        });
    }
}

