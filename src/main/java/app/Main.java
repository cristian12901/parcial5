/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import controlador.ControladorBatalla;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import modelo.Monstruo;
import modelo.MonstruoEfecto;
import modelo.Posicion;
import vista.VistaBatalla;
import javax.swing.UIManager;


/**
 *
 * @author alex
 */

public class Main {

    public static void main(String[] args) {
        // 1. Generación de reportes requeridos sobre estructuras dinámicas
        generarReportesIniciales();

        // 2. Activar estilo visual moderno (Nimbus) antes de abrir la ventana
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // Si falla, usa el diseño por defecto de Java
        }

        // 3. Inicio de la interfaz gráfica MVC
        SwingUtilities.invokeLater(() -> {
            VistaBatalla vista = new VistaBatalla();
            ControladorBatalla controlador = new ControladorBatalla(vista);
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }

    private static void generarReportesIniciales() {
        List<Monstruo> mazoBase = new ArrayList<>();
        mazoBase.add(new Monstruo("Mago Oscuro", 2500, 2100, Posicion.ATAQUE));
        mazoBase.add(new Monstruo("Dragón Blanco de Ojos Azules", 3000, 2500, Posicion.ATAQUE));
        mazoBase.add(new Monstruo("Cráneo Convocado", 2500, 1200, Posicion.ATAQUE));
        mazoBase.add(new Monstruo("Elfo Místico", 800, 2000, Posicion.DEFENSA));
        mazoBase.add(new MonstruoEfecto("Kuriboh", 300, 200, Posicion.DEFENSA, "Descarta esta carta para evitar daño."));

        // Reporte 1: Monstruos con ataque superior a 2000 puntos
        System.out.println("=== REPORTE 1: MONSTRUOS DE ALTO ATAQUE (> 2000 ATQ) ===");
        for (Monstruo monstruo : mazoBase) {
            if (monstruo.getAtq() > 2000) {
                System.out.println("- " + monstruo.toString());
            }
        }

        // Reporte 2: Conteo de monstruos según su posición
        int enAtaque = 0;
        int enDefensa = 0;
        for (Monstruo monstruo : mazoBase) {
            if (monstruo.getPosicion() == Posicion.ATAQUE) {
                enAtaque++;
            } else {
                enDefensa++;
            }
        }

        System.out.println("\n=== REPORTE 2: DISTRIBUCIÓN POR POSICIÓN ===");
        System.out.println("Total de cartas en el mazo: " + mazoBase.size());
        System.out.println("Monstruos en Ataque: " + enAtaque);
        System.out.println("Monstruos en Defensa: " + enDefensa);
        System.out.println("=============================================\n");
    }
}
