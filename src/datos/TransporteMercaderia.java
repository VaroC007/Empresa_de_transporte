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
public class TransporteMercaderia extends Transporte {

//    private double toneladas;
//    private boolean esPeligroso;
    
    private static final int TAMREG = 52;
    private static int TAMARCHIVO = 100;

    public TransporteMercaderia() {
        super();
        this.toneladas = 0.0;
        this.esPeligroso = false;
        this.tipo = 'M';
    }

    public double getToneladas() {
        return toneladas;
    }

    public boolean isEsPeligroso() {
        return esPeligroso;
    }

    @Override
    public void cargarDatos(int val) {
        super.cargarDatos(val);
        this.extra = calcularExtra();
        cargarToneladas();
        cargarEsPeligroso();
        this.personas = 0;
    }
    
    public void setToneladas(double toneladas) {
        if (toneladas < 0) {
            throw new IllegalArgumentException("Las toneladas no pueden ser negativas.");
        }
        this.toneladas = toneladas;
    }

    public void setEsPeligroso(boolean esPeligroso) {
        this.esPeligroso = esPeligroso;
    }

    private void cargarToneladas() {
        double t;
        do {
            Consola.emitirMensaje("Toneladas transportadas: ");
            t = Consola.leerDouble();
        } while (t <= 0);
        this.toneladas = t;
    }

    private void cargarEsPeligroso() {
        char resp;
        do {
            Consola.emitirMensaje("¿Es mercadería peligrosa? (S/N): ");
            resp = Consola.leerChar();
        } while (!(resp == 'S' || resp == 's' || resp == 'N' || resp == 'n'));

        this.esPeligroso = (resp == 'S' || resp == 's');
    }

    @Override
    public double calcularExtra() {
        double base = 7000 * toneladas;
        if (esPeligroso) {
            base += 20000; // adicional fijo
        }
        return base;
    }

    @Override
    public String toString() {
        String peligro = esPeligroso ? " - Peligroso" : "";
        return super.toString() + String.format(" %d toneladas%s", toneladas, peligro);
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
        return TAMREG;
    }
 /*   
    @Override
    public void grabar(RandomAccessFile file) {
        super.grabar(file);
        try {
            file.writeInt(0);                 // dummy personas
            file.writeDouble(toneladas);      // valor real
            file.writeBoolean(esPeligroso);   // valor real
        } catch (IOException e) {
            Consola.emitirMensajeLN("Error al grabar transporte" + e.getMessage());
            System.exit(1);
        }
    }
    
    @Override
    public void leer(RandomAccessFile file, int val) {
        super.leer(file, 0);
        try {
            
            file.readInt(); // salto personas dummy
            this.toneladas = file.readDouble();
            this.esPeligroso = file.readBoolean();
        } catch (IOException e) {
            Consola.emitirMensajeLN("error al leer el registro: " + e.getMessage());
            System.exit(1);
        }
    }
    */
    @Override
    public void agregarCod(int i){
        super.agregarCod(i);
    }
    
    @Override
    public void agregarDni(long dni){
        super.agregarDni(dni);
    }

}
