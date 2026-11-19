/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alex
 */
public class Monstruo extends Carta implements Atacable {

    private int atq;
    private int def;
    private Posicion posicion;

    public Monstruo(String nombre, int atq, int def, Posicion posicion) {
        super(nombre);
        
        if (atq < 0) {
            throw new IllegalArgumentException("El ataque (ATQ) no puede ser negativo.");
        }
        if (def < 0) {
            throw new IllegalArgumentException("La defensa (DEF) no puede ser negativa.");
        }
        if (posicion == null) {
            throw new IllegalArgumentException("La posición es obligatoria.");
        }
        
        this.atq = atq;
        this.def = def;
        this.posicion = posicion;
    }

    @Override
    public int getAtq() {
        return atq;
    }

    @Override
    public int getDef() {
        return def;
    }

    @Override
    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        if (posicion == null) {
            throw new IllegalArgumentException("La posición es obligatoria.");
        }
        this.posicion = posicion;
    }

    @Override
    public String getDescripcion() {
        return "Monstruo normal";
    }

    @Override
    public String toString() {
        return getNombre() + " [ATQ " + atq + " / DEF " + def + "] (" + posicion + ")";
    }
}
