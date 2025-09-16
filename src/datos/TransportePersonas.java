/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.io.IOException;
import java.io.RandomAccessFile;
import tpg2_prog_2025.Consola;

/**
 *
 * @author Julian Novoa
 */
public class TransportePersonas extends Transporte {

    private int personas;
    
    
    private static int TAMAREG = 35;
    private static int TAMARCHIVO = 100;
    
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
    
    @Override
    public void grabar(RandomAccessFile file) {
        super.grabar(file);
        try {
            file.writeInt(personas);
            file
        } catch (IOException e) {
            Consola.emitirMensajeLN("Error al grabar transporte personas" + e.getMessage());
            System.exit(1);
        }
    }
    
    @Override
    public void leer(RandomAccessFile file, int val) {
        super.leer(file, val);
        try {
            if (val == 0) {
                codT = file.readInt();
            }
            personas = file.readInt();
            primitivoVacio = file.readInt();
            charvacio = file.readChar();
            char2vacio = file.readChar();

        } catch (IOException e) {
            Consola.emitirMensajeLN("error al leer el registro: " + e.getMessage());
            System.exit(1);
        }
    }
    
    @Override
    public void agregarCod(int i){
        super.agregarCod(i);
    }
    
    @Override
    public void agregarDni(long dni){
        super.agregarDni(dni);
    }

}
