/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.RandomAccessFile;
import persistencia.*;
import tpg2_prog_2025.Consola;

/**
 *
 * @author alvar
 */
public class Conductor implements Grabable {

    private long dni;
    private String Ape_Nom;

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
    public void cargarDatos() {
        cargarDni();
        cargarNombre();
    }

    @Override
    public int tamArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int tamRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void grabar(RandomAccessFile a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leer(RandomAccessFile a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Conductor {"
                + ", DNI=" + dni
                + ", Nombre=" + Ape_Nom
                + '}';
    }

}
