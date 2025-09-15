/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import tpg2_prog_2025.Consola;

/**
 *
 * @author Julian Novoa
 */
public class TransportePersonas extends Transporte {

    private int personas;

    public TransportePersonas() {
        super();
        this.personas = 0;
    }

    public int getPersonas() {
        return personas;
    }

    private void setPersonas(int personas) {
        if (personas < 0) {
            throw new IllegalArgumentException("La cantidad de personas no puede ser negativa.");
        }
        this.personas = personas;
    }

    private void cargarPersonas() {
        int p;
        do {
            Consola.emitirMensaje("Cantidad de personas: ");
            p = Consola.leerInt();
        } while (p <= 0);
        this.personas = p;
    }

    @Override
    public void cargarDatos(int val) {
        super.cargarDatos(val);
        cargarPersonas();
        this.extra = calcularExtra();
    }

    @Override
    public double calcularExtra() {
        if (personas > 9) {
            return 5500 * this.horas;
        } else {
            return 3000 * this.horas;
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %d personas", personas);
    }

    @Override
    public void mostrarRegistro(int val, boolean activo) {
        if (!activo) {
            return;
        }

        switch (val) {
            case 0:
                super.mostrarRegistro(0, true);
                break;
            case 1:
                Consola.emitirMensaje(toString());
                break;
        }
    }
    
    @Override
    public int tamRegistro(){
        return super.tamRegistro();
    }

}
