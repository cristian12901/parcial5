/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */


public class Jugador {

    public static final int LP_INICIALES = 8000;

    private final String nombre;
    private int lp;
    private final List<Monstruo> campo;

    public Jugador(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío.");
        }
        this.nombre = nombre.trim();
        this.lp = LP_INICIALES;
        this.campo = new ArrayList<>();
    }

    public Jugador(String nombre, int lpIniciales) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío.");
        }
        if (lpIniciales < 0) {
            throw new IllegalArgumentException("Los LP iniciales no pueden ser negativos.");
        }
        this.nombre = nombre.trim();
        this.lp = lpIniciales;
        this.campo = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getLp() {
        return lp;
    }

    public List<Monstruo> getCampo() {
        return campo;
    }

    public void invocar(Monstruo monstruo) {
        if (monstruo == null) {
            throw new IllegalArgumentException("El monstruo no puede ser nulo.");
        }
        if (campo.size() >= 5) {
            throw new IllegalStateException("El campo está lleno (máximo 5 monstruos por jugador).");
        }
        campo.add(monstruo);
    }

    public boolean retirar(Monstruo monstruo) {
        return campo.remove(monstruo);
    }

    public boolean tieneMonstruos() {
        return !campo.isEmpty();
    }

    public void recibirDanio(int danio) {
        if (danio < 0) {
            throw new IllegalArgumentException("El daño no puede ser negativo.");
        }
        
        this.lp = this.lp - danio;
        if (this.lp < 0) {
            this.lp = 0;
        }
    }

    public boolean estaDerrotado() {
        return lp <= 0;
    }

    @Override
    public String toString() {
        return nombre + " (LP: " + lp + ")";
    }
}
