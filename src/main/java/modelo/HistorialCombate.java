/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistorialCombate {

    public static final String ARCHIVO_POR_DEFECTO = "historial_combate.txt";

    private final File archivo;
    private final DateTimeFormatter formatoFecha;

    public HistorialCombate() {
        this.archivo = new File(ARCHIVO_POR_DEFECTO);
        this.formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public HistorialCombate(Path rutaArchivo) {
        this.archivo = rutaArchivo.toFile();
        this.formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public void registrar(ResultadoCombate resultado) throws IOException {
        // El true en FileWriter indica que se agregará al final sin borrar lo anterior
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
            String fechaActual = LocalDateTime.now().format(formatoFecha);
            escritor.write("[" + fechaActual + "] " + resultado.toString());
            escritor.newLine();
        }
    }
}
