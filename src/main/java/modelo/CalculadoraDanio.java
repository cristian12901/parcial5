/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alex
 */
public class CalculadoraDanio {

    // Calcula el resultado cuando un monstruo ataca a otro monstruo en el campo
    public static ResultadoCombate calcular(Monstruo atacante, Monstruo defensor) {
        validarAtacante(atacante);
        if (defensor == null) {
            throw new IllegalArgumentException("El monstruo defensor no puede ser nulo.");
        }

        String nombreAtacante = atacante.getNombre();
        String nombreDefensor = defensor.getNombre();

        // Caso 1: El monstruo defensor está en posición de ataque
        if (defensor.getPosicion() == Posicion.ATAQUE) {
            int ataqueA = atacante.getAtq();
            int ataqueD = defensor.getAtq();

            if (ataqueA > ataqueD) {
                int danio = ataqueA - ataqueD;
                return new ResultadoCombate(nombreAtacante, nombreDefensor, danio, Objetivo.DEFENSOR, 
                        false, true, "Defensor destruido; el defensor pierde " + danio + " LP");
                        
            } else if (ataqueA < ataqueD) {
                int danio = ataqueD - ataqueA;
                return new ResultadoCombate(nombreAtacante, nombreDefensor, danio, Objetivo.ATACANTE, 
                        true, false, "Atacante destruido; el atacante pierde " + danio + " LP");
                        
            } else {
                return new ResultadoCombate(nombreAtacante, nombreDefensor, 0, Objetivo.NINGUNO, 
                        true, true, "Ambos monstruos destruidos; sin daño");
            }
        }

        // Caso 2: El monstruo defensor está en posición de defensa
        int ataque = atacante.getAtq();
        int defensa = defensor.getDef();

        if (ataque > defensa) {
            return new ResultadoCombate(nombreAtacante, nombreDefensor, 0, Objetivo.NINGUNO, 
                    false, true, "Defensor destruido; sin daño a los LP");
                    
        } else if (ataque < defensa) {
            int danio = defensa - ataque;
            return new ResultadoCombate(nombreAtacante, nombreDefensor, danio, Objetivo.ATACANTE, 
                    false, false, "Ataque rechazado; el atacante pierde " + danio + " LP");
                    
        } else {
            return new ResultadoCombate(nombreAtacante, nombreDefensor, 0, Objetivo.NINGUNO, 
                    false, false, "Empate; no pasa nada");
        }
    }

    // Sobrecarga para cuando se hace un ataque directo al jugador
    public static ResultadoCombate calcular(Monstruo atacante) {
        validarAtacante(atacante);
        int danioDirecto = atacante.getAtq();
        
        return new ResultadoCombate(atacante.getNombre(), "Ataque directo", danioDirecto,
                Objetivo.DEFENSOR, false, false,
                "Ataque directo; el defensor pierde " + danioDirecto + " LP");
    }

    private static void validarAtacante(Monstruo atacante) {
        if (atacante == null) {
            throw new IllegalArgumentException("El monstruo atacante no puede ser nulo.");
        }
        if (atacante.getPosicion() != Posicion.ATAQUE) {
            throw new IllegalStateException("Solo un monstruo en posición de ataque puede atacar.");
        }
    }
}
