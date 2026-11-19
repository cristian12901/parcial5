/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alex
 */


public class HistorialCombateTest {

    @Test
    public void testRegistrarAtaquesEnHistorial() throws IOException {
        File archivoPrueba = new File("test_historial_combate.txt");
        
        // Nos aseguramos de que el archivo no exista antes de empezar la prueba
        if (archivoPrueba.exists()) {
            archivoPrueba.delete();
        }

        HistorialCombate historial = new HistorialCombate(archivoPrueba.toPath());
        Monstruo mago = new Monstruo("Mago Oscuro", 2000, 1000, Posicion.ATAQUE);
        Monstruo dragon = new Monstruo("Dragón", 1500, 1000, Posicion.ATAQUE);

        historial.registrar(CalculadoraDanio.calcular(mago, dragon));
        historial.registrar(CalculadoraDanio.calcular(mago));

        List<String> lineas = Files.readAllLines(archivoPrueba.toPath());
        assertEquals(2, lineas.size());
        assertTrue(lineas.get(0).contains("Atacante: Mago Oscuro"));
        assertTrue(lineas.get(0).contains("Daño: 500"));
        assertTrue(lineas.get(1).contains("Ataque directo"));

        // Borramos el archivo temporal al terminar
        archivoPrueba.delete();
    }
}
