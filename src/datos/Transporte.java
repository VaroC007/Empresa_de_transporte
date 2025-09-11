/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import Principal.Consola;
import persistencia.Grabable;
import persistencia.Registro;
import java.io.*;

/**
 *
 * @author Julian Novoa
 */
public abstract class Transporte implements Grabable {

    protected int codT;
    protected char tipo; //p personas, m mercaderias
    protected int horas;
    protected long dniConductor;
    protected double extra;  //monto calculado
    protected boolean estado; // activo inactivo

    private static int TAMARCHIVO = 100;
    private static int TAMREG = 26; //bytes

    public Transporte() {
        this.codT = 0;
        this.tipo = 'p';
        this.dniConductor = 0;
        this.extra = 0.0;
        this.estado = true;
    }

    @Override
    public int tamRegistro() {
        return TAMREG;
    }

    @Override
    public int tamArchivo() {
        return TAMARCHIVO;
    }

    @Override
    public void cargarDatos() {
        cargarCodT();
        cargarTipo();
        cargarHoras();
        cargarDniConductor();
        cargarExtra();
        this.estado = true;
    }

    public void cargarDatosSinClave() {
        cargarTipo();
        cargarHoras();
        cargarDniConductor();
        cargarExtra();
        this.estado = true;
    }

    private void cargarCodT() {
        int c;
        do {
            Consola.emitirMensaje("Codigo de transporte: ");

            c = Consola.leerInt();
        } while (c < 0 && c > 100);
        this.codT = c;
    }

    private void cargarTipo() {
        char t;
        do {
            Consola.emitirMensaje("Tipo de transporte P=personas, M=mercaderias:");
            t = Consola.leerChar();
        } while (!(t == 'p' || t == 'P' || t == 'm' || t == 'M'));
        this.tipo = t;
    }

    private void cargarHoras() {
        int h;
        do {
            Consola.emitirMensaje("Horas conducidas: ");
            h = Consola.leerInt();
        } while (h <= 0);
        this.horas = h;
    }

    private void cargarDniConductor() {
        int dc;
        do {
            Consola.emitirMensaje("Dni conductor: ");
            dc = Consola.leerInt();
        } while (dc <= 0);
        this.dniConductor = dc;
    }

    private void cargarExtra() {
        double e;
        do {
            Consola.emitirMensaje("Extra: $");
            e = Consola.leerDouble();
        } while (e <= 0);
        this.extra = e;
    }

    @Override
    public void grabar(RandomAccessFile file) {
        try {
            file.writeInt(codT);
            file.writeChar(tipo);
            file.writeInt(horas);
            file.writeLong(dniConductor);
            file.writeDouble(extra);
        } catch (IOException e) {
            Consola.emitirMensajeLN("Error al grabar transporte" + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void leer(RandomAccessFile file) {
        try {
            codT = file.readInt();
            tipo = file.readChar();
            horas = file.readInt();
            dniConductor = file.readLong();
            extra = file.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mostrarRegistro() {
        Consola.emitirMensajeLN(toString());
    }

    @Override
    public String toString() {
        String tipoStr = (tipo == 'P' || tipo == 'p') ? "Personas" : "Mercaderias";
        String estadoStr = estado ? "Activo" : "Inactivo";

        return "Transporte {"
                + "Codigo=" + codT
                + ", Tipo=" + tipoStr
                + ", Horas=" + horas
                + ", DNI Conductor=" + dniConductor
                + ", Extra=$" + extra
                + ", Estado=" + estadoStr
                + '}';
    }
}
