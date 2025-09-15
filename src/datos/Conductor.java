/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.RandomAccessFile;
import persistencia.*;
import tpg2_prog_2025.Consola;

/**
 *
 * @author alvar
 */
public class Conductor implements Grabable {

    private long dni;
    private String Ape_Nom; //cadena de 20 caracteres

    private static int TAMAREG = 48;
    private static int TAMARCHIVO = 100;

    public Conductor() {
        this.dni = 0;
        this.Ape_Nom = "";
    }

    public long getDni() {
        return dni;
    }

    public String getApe_Nom() {
        return Ape_Nom;
    }

    public void setDni(long dni) {
        if (dni <= 0) {
            throw new IllegalArgumentException("El dni no puede ser menor o igual a cero.");
        }
        this.dni = dni;
    }
    public void agregarDni(long x){
        setDni(x);
    }

    public void setApe_Nom(String Ape_Nom) {
        if (Ape_Nom.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacio");
        }
        this.Ape_Nom = Ape_Nom;
    }

    private void cargarDni() {
        int d;
        do {
            Consola.emitirMensaje("Dni del conductor: ");
            d = Consola.leerInt();
        } while (d <= 0);
        this.dni = d;
    }

    private void cargarNombre() {
        String n;
        do {
            Consola.emitirMensaje("Nombre y apellido del conductor: ");
            n = Consola.leerString();
        } while (n.trim().isEmpty());
        this.Ape_Nom = n;
    }

    @Override
    public void cargarDatos(int val) {
        if (val == 0) {
            cargarDni();
        }
        cargarNombre();
    }

    @Override
    public int tamArchivo() {
        return TAMARCHIVO;
    }

    @Override
    public int tamRegistro() {
        return TAMAREG;
    }

    @Override
    public void grabar(RandomAccessFile a) {
        try {
            a.writeLong(dni);
            Registro.writeString(a, Ape_Nom, 20);
        } catch (IOException e) {
            Consola.emitirMensajeLN("Error al grabar registro: " + e.getMessage());
        }
    }

    @Override
    public void leer(RandomAccessFile a, int val) {
        try {
            if (val == 0) {
                this.dni = a.readLong();
            }
            this.Ape_Nom = Registro.leerString(a, 20).trim();
        } catch (IOException e) {
            Consola.emitirMensajeLN("Error al leer el registro: " + e.getMessage());
        }
    }

    @Override
    public void mostrarRegistro(int val, boolean activo) {
        if (!activo) {
            return;
        }
        switch (val) {
            case 0:
                Consola.emitirMensaje(String.format("%-12s %-20s", "DNI", "Conductor"));
                break;
            case 1:
                Consola.emitirMensaje(toString());
                break;
            case 2:
                Consola.emitirMensajeLN("Consductor: " + this.Ape_Nom);
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("%-12d %-20s", dni, Ape_Nom);
    }
    
    ///METODO PARA SETTEAR DNI. UTILIZADO EN LA CARGA. MANTIENE ENCAPSULAMIENTO///
    public void agregarDNI(long x){
        setDni(x);
    }

}
