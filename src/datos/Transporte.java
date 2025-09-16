/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.io.IOException;
import java.io.RandomAccessFile;
import persistencia.Grabable;
import tpg2_prog_2025.Consola;

/**
 *
 * @author Julian Novoa
 */
public abstract class Transporte implements Grabable, ICalculable {

    protected int codT;
    protected char tipo; //p personas, m mercaderias
    protected int horas;
    protected long dniConductor;
    protected double extra;  //monto calculado
    
    private static int TAMARCHIVO = 100;
    private static int TAMREG = 52; //bytes

    public Transporte() {
        this.codT = 0;
        this.tipo = 'p';
        this.dniConductor = 0;
        this.extra = 0.0;

    }

    public long getDniConductor() {
        return dniConductor;
    }

    public int getCodT() {
        return codT;
    }

    public char getTipo() {
        return tipo;
    }

    public int getHoras() {
        return horas;
    }

    public double getExtra() {
        return extra;
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
    public void cargarDatos(int val) {
        if (val == 0) {
            cargarCodT();
        }
        cargarTipo();
        cargarHoras();
       // cargarDniConductor();
    }

    /*public void cargarDatosSinClave() {
        cargarTipo();
        cargarHoras();
        cargarDniConductor();
    }*/
    public void cargarCodT() {
        int c;
        do {
            Consola.emitirMensaje("Codigo de transporte: ");

            c = Consola.leerInt();
        } while (c < 0 && c > 100);
        this.codT = c;
    }
    /*METODO PARA SETTEAR CODIGO, UTILIZADO EN LAS CARGAS. MANTIENE EL ENCAPSULAMIENTO*/
    public void agregarCod(int cod){
        codT = cod;
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
    /**METODO PARA SETTEAR DNI, UTILIZADO PARA LAS CARGAS MANTIENE EL ENCAPSULAMIENTO
     * @param dni**********************************/
    public void agregarDni(long dni){
        dniConductor = dni;
    }
    //

    /*private void cargarExtra() {
        double e;
        do {
            Consola.emitirMensaje("Extra: $");
            e = Consola.leerDouble();
        } while (e <= 0);
        this.extra = e;
    }*/
    @Override
    public abstract double calcularExtra();

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
    public void leer(RandomAccessFile file, int val) {
        try {
            if (val == 0) {
                codT = file.readInt();
            }
            tipo = file.readChar();
            horas = file.readInt();
            dniConductor = file.readLong();
            extra = file.readDouble();
        } catch (IOException e) {
            Consola.emitirMensajeLN("error al leer el registro: " + e.getMessage());
            System.exit(1);
        }
    }

    public boolean equals(Transporte x) {
        if (x == null) {
            return false;
        }
        return (x.codT == this.codT);
    }

    @Override
    public void mostrarRegistro(int val, boolean activo) {
        if (!activo) {
            return;
        }

        switch (val) {
            case 0:
                Consola.emitirMensaje("Cod.T        Tipo        Extra        Detalle");
                break;
            case 1:
                Consola.emitirMensaje(toString());
                break;
            case 2:
                String tipoSimple = (tipo == 'P' || tipo == 'p') ? "Personas" : "Mercaderias";
                System.out.println("Transporte: (" + codT + ", " + tipoSimple + ")");
                break;
        }
    }

    @Override
    public String toString() {
        String tipoStr = (tipo == 'P' || tipo == 'p') ? "Personas" : "Mercaderias";
        return String.format("%-10d %-15s $%-12.2f", codT, tipoStr, extra);

    }
    
    
}
