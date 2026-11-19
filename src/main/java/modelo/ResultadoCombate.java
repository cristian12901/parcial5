/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author alex
 */
public class ResultadoCombate {

    private String atacante;
    private String defensor;
    private int danio;
    private Objetivo objetivoDanio;
    private boolean atacanteDestruido;
    private boolean defensorDestruido;
    private String descripcion;

    public ResultadoCombate(String atacante, String defensor, int danio, Objetivo objetivoDanio,
            boolean atacanteDestruido, boolean defensorDestruido, String descripcion) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.danio = danio;
        this.objetivoDanio = objetivoDanio;
        this.atacanteDestruido = atacanteDestruido;
        this.defensorDestruido = defensorDestruido;
        this.descripcion = descripcion;
    }

    public String getAtacante() {
        return atacante;
    }

    public String getDefensor() {
        return defensor;
    }

    public int getDanio() {
        return danio;
    }

    public Objetivo getObjetivoDanio() {
        return objetivoDanio;
    }

    public boolean isAtacanteDestruido() {
        return atacanteDestruido;
    }

    public boolean isDefensorDestruido() {
        return defensorDestruido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Atacante: " + atacante + " | Defensor: " + defensor
                + " | Daño: " + danio + " | Resultado: " + descripcion;
    }
}
