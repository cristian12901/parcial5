/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alex
 */
public class Combate {

    public ResultadoCombate atacar(Jugador jugadorAtacante, Monstruo monstruoAtacante,
            Jugador jugadorDefensor, Monstruo monstruoDefensor) {
        
        validarPertenencia(jugadorAtacante, monstruoAtacante);
        validarPertenencia(jugadorDefensor, monstruoDefensor);

        ResultadoCombate resultado = CalculadoraDanio.calcular(monstruoAtacante, monstruoDefensor);
        aplicarResultado(resultado, jugadorAtacante, monstruoAtacante, jugadorDefensor, monstruoDefensor);
        
        return resultado;
    }

    public ResultadoCombate atacarDirecto(Jugador jugadorAtacante, Monstruo monstruoAtacante,
            Jugador jugadorDefensor) {
        
        validarPertenencia(jugadorAtacante, monstruoAtacante);
        
        if (jugadorDefensor == null) {
            throw new IllegalArgumentException("El jugador defensor no puede ser nulo.");
        }
        if (jugadorDefensor.tieneMonstruos()) {
            throw new IllegalStateException("No puedes hacer un ataque directo mientras el rival tenga monstruos.");
        }

        ResultadoCombate resultado = CalculadoraDanio.calcular(monstruoAtacante);
        aplicarResultado(resultado, jugadorAtacante, monstruoAtacante, jugadorDefensor, null);
        
        return resultado;
    }

    private void aplicarResultado(ResultadoCombate resultado, Jugador atacante, Monstruo cartaAtacante,
            Jugador defensor, Monstruo cartaDefensora) {
        
        // Aplicar el daño a los puntos de vida del jugador que corresponda
        if (resultado.getObjetivoDanio() == Objetivo.ATACANTE) {
            atacante.recibirDanio(resultado.getDanio());
        } else if (resultado.getObjetivoDanio() == Objetivo.DEFENSOR) {
            defensor.recibirDanio(resultado.getDanio());
        }

        // Retirar del campo los monstruos que fueron destruidos
        if (resultado.isAtacanteDestruido()) {
            atacante.retirar(cartaAtacante);
        }
        
        if (resultado.isDefensorDestruido() && cartaDefensora != null) {
            defensor.retirar(cartaDefensora);
        }
    }

    private void validarPertenencia(Jugador jugador, Monstruo monstruo) {
        if (jugador == null || monstruo == null) {
            throw new IllegalArgumentException("El jugador y el monstruo son obligatorios.");
        }
        if (!jugador.getCampo().contains(monstruo)) {
            throw new IllegalArgumentException("Ese monstruo no está en el campo del jugador.");
        }
    }
}
