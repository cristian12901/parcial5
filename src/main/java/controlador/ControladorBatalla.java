/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.Combate;
import modelo.HistorialCombate;
import modelo.Jugador;
import modelo.Monstruo;
import modelo.MonstruoEfecto;
import modelo.Posicion;
import modelo.ResultadoCombate;
import vista.VistaBatalla;

/**
 *
 * @author alex
 */



public class ControladorBatalla {

    private VistaBatalla vista;
    private Combate combate;
    private HistorialCombate historial;
    private Jugador[] jugadores;

    public ControladorBatalla(VistaBatalla vista) {
        this.vista = vista;
        this.combate = new Combate();
        this.historial = new HistorialCombate();
        
        // Al iniciar, los botones de pelea deben estar bloqueados
        this.vista.setBatallaHabilitada(false);
        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.addCrearJugadoresListener(e -> crearJugadores());
        vista.addInvocarListener(e -> invocarMonstruo());
        vista.addAtacarListener(e -> atacar());
        vista.addAtaqueDirectoListener(e -> atacarDirecto());
    }

    private void crearJugadores() {
        try {
            String nombre1 = vista.getNombreJ1().trim();
            String nombre2 = vista.getNombreJ2().trim();

            if (nombre1.equalsIgnoreCase(nombre2)) {
                vista.mostrarError("Los dos jugadores no pueden tener el mismo nombre.");
                return;
            }

            String textoLp = vista.getLpInicial().trim();
            int lpInicial = Integer.parseInt(textoLp);
            
            Jugador jugador1 = new Jugador(nombre1, lpInicial);
            Jugador jugador2 = new Jugador(nombre2, lpInicial);
            
            jugadores = new Jugador[2];
            jugadores[0] = jugador1;
            jugadores[1] = jugador2;
            
            vista.limpiarHistorial();
            vista.setBatallaHabilitada(true);
            actualizarVista();
            
        } catch (NumberFormatException e) {
            vista.mostrarError("Los puntos de vida deben ser un número entero.");
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }


    private void invocarMonstruo() {
        if (!verificarJugadores()) {
            return;
        }
        
        try {
            String nombre = vista.getNombreMonstruo();
            int ataque = Integer.parseInt(vista.getAtq().trim());
            int defensa = Integer.parseInt(vista.getDef().trim());
            
            Posicion posicion;
            if (vista.getIndicePosicion() == 0) {
                posicion = Posicion.ATAQUE;
            } else {
                posicion = Posicion.DEFENSA;
            }
            
            String efecto = vista.getEfecto().trim();
            Monstruo monstruo;
            
            if (efecto.isEmpty()) {
                monstruo = new Monstruo(nombre, ataque, defensa, posicion);
            } else {
                monstruo = new MonstruoEfecto(nombre, ataque, defensa, posicion, efecto);
            }

            int turnoJugador = vista.getIndiceJugadorInvocador();
            jugadores[turnoJugador].invocar(monstruo);
            
            vista.limpiarFormularioMonstruo();
            actualizarVista();
            
        } catch (NumberFormatException e) {
            vista.mostrarError("ATQ y DEF tienen que ser números enteros.");
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void atacar() {
        if (!verificarJugadores()) {
            return;
        }
        
        int posAtacante = vista.getIndiceAtacante();
        int posDefensor;
        if (posAtacante == 0) {
            posDefensor = 1;
        } else {
            posDefensor = 0;
        }
        
        Jugador atacante = jugadores[posAtacante];
        Jugador defensor = jugadores[posDefensor];
        
        int indexMonstruoAtacante = vista.getIndiceSeleccionado(posAtacante);
        int indexMonstruoDefensor = vista.getIndiceSeleccionado(posDefensor);
        
        if (indexMonstruoAtacante < 0 || indexMonstruoDefensor < 0) {
            vista.mostrarError("Tienes que seleccionar un monstruo atacante y uno objetivo.");
            return;
        }
        
        try {
            Monstruo mAtacante = atacante.getCampo().get(indexMonstruoAtacante);
            Monstruo mDefensor = defensor.getCampo().get(indexMonstruoDefensor);
            
            ResultadoCombate resultado = combate.atacar(atacante, mAtacante, defensor, mDefensor);
            finalizarTurnoAtaque(resultado);
            
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void atacarDirecto() {
        if (!verificarJugadores()) {
            return;
        }
        
        int posAtacante = vista.getIndiceAtacante();
        int posDefensor;
        if (posAtacante == 0) {
            posDefensor = 1;
        } else {
            posDefensor = 0;
        }
        
        Jugador atacante = jugadores[posAtacante];
        Jugador defensor = jugadores[posDefensor];
        
        int indexMonstruoAtacante = vista.getIndiceSeleccionado(posAtacante);
        
        if (indexMonstruoAtacante < 0) {
            vista.mostrarError("Selecciona el monstruo con el que vas a atacar.");
            return;
        }
        
        try {
            Monstruo mAtacante = atacante.getCampo().get(indexMonstruoAtacante);
            ResultadoCombate resultado = combate.atacarDirecto(atacante, mAtacante, defensor);
            finalizarTurnoAtaque(resultado);
            
        } catch (IllegalStateException e) {
            vista.mostrarError(e.getMessage());
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void finalizarTurnoAtaque(ResultadoCombate resultado) {
        try {
            historial.registrar(resultado);
        } catch (IOException e) {
            vista.mostrarError("No se pudo guardar el historial: " + e.getMessage());
        }
        
        vista.agregarHistorial(resultado.toString());
        actualizarVista();
        revisarGanador();
    }

    private void revisarGanador() {
        if (jugadores[0].estaDerrotado()) {
            vista.mostrarMensaje("¡" + jugadores[1].getNombre() + " gana el duelo!");
            vista.setBatallaHabilitada(false);
        } else if (jugadores[1].estaDerrotado()) {
            vista.mostrarMensaje("¡" + jugadores[0].getNombre() + " gana el duelo!");
            vista.setBatallaHabilitada(false);
        }
    }

    private boolean verificarJugadores() {
        if (jugadores == null) {
            vista.mostrarError("Primero debes crear los jugadores.");
            return false;
        }
        return true;
    }

    private void actualizarVista() {
        for (int i = 0; i < jugadores.length; i++) {
            vista.setNombre(i, jugadores[i].getNombre());
            vista.setLp(i, jugadores[i].getLp());
            
            List<String> listaCartas = new ArrayList<>();
            for (Monstruo m : jugadores[i].getCampo()) {
                listaCartas.add(m.toString());
            }
            vista.setCampo(i, listaCartas);
        }
    }
}
