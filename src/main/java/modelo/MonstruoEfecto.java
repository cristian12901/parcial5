/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alex
 */
public class MonstruoEfecto extends Monstruo {

    private String efecto;

    public MonstruoEfecto(String nombre, int atq, int def, Posicion posicion, String efecto) {
        super(nombre, atq, def, posicion);
        
        if (efecto == null) {
            this.efecto = "";
        } else {
            this.efecto = efecto;
        }
    }

    public String getEfecto() {
        return efecto;
    }

    @Override
    public String getDescripcion() {
        return "Monstruo de efecto: " + efecto;
    }
}}
