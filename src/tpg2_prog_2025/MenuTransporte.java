/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpg2_prog_2025;

import datos.*;
import persistencia.*;
import tpg2_prog_2025.GestorEmpresa.*;

public class MenuTransporte {

    private static Archivo archiTransp;

    public MenuTransporte() {
        try {
            archiTransp = new Archivo("TRANSPORTES.dat", new TransporteMercaderia());
        } catch (ClassNotFoundException e) {
            Consola.emitirMensajeLN("Error al crear el archivo de transportes: " + e.getMessage());
            System.exit(1);
        }
    }

    public void mostrarMenuTransporte() {
        Consola.emitirMensajeLN("-ACTUALIZACION DE TRANSPORTE-");
        Consola.emitirMensajeLN("1. Alta de transporte");
        Consola.emitirMensajeLN("2. Baja de transporte");
        Consola.emitirMensajeLN("3. Modificacion de transporte");
        Consola.emitirMensajeLN("0. Salir");
        Consola.emitirMensaje("Elija una opcion: ");
    }

    public void actualizacionTransporte() {
        int op;
        archiTransp.abrirParaLeerEscribir();

        do {
            mostrarMenuTransporte();
            op = Consola.leerInt();
            switch (op) {
                case 1:
                    cargaTransporte();
                    break;
                case 2:
                    bajaDeTransporte();
                    break;
                case 3:
                    modificarTransporte();
                    break;
            }
        } while (op != 0);

        archiTransp.cerrarArchivo();
    }

    private void cargaTransporte() {
        try {
            do {
                Registro reg = leerDatosTransp();
                archiTransp.cargarUnRegistro(reg);
                System.out.println("menutransporte se cargò el transporte en ek archivo");
            } while (Consola.confirmar());
        } catch (Exception e) {
            Consola.emitirMensajeLN("Error al cargar transporte: " + e.getMessage());
        }
    }

    private  Registro leerDatosTransp() {
        Consola.emitirMensajeLN("1. Transporte para personas");
        Consola.emitirMensajeLN("2. Transporte de mercaderia");
        Consola.emitirMensaje("Elija una opcion: ");
        int tipo = Consola.leerInt();

        Transporte t;
        long dni;
        int cod;

        // Validar DNI
        do {
            Consola.emitirMensajeLN("DNI del conductor: ");
            dni = (long)Consola.leerInt();
            if (GestorEmpresa.existeDni(dni) == null) {
                Consola.emitirMensajeLN("Conductor inexistente.");
                dni = -1;
            }
        } while (dni < 0);

        // Validar código
        do {
            Consola.emitirMensajeLN("Codigo del transporte: ");
            cod = Consola.leerInt();
            if (obtenerTransporte(cod) != null) {
                Consola.emitirMensajeLN("Alta existente.");
                cod = -1;
            }
        } while (cod < 0);

        if (tipo == 1) t = new TransportePersonas();
        else t = new TransporteMercaderia();
        if (t instanceof TransportePersonas){
            System.out.println("menutransporte. t es de tipo persona");
        }
        t.agregarCod(cod);
        t.agregarDni(dni);
        t.cargarDatos(1); // carga de atributos propios

        Registro reg = new Registro(t, t.getCodT());
       // reg.setEstado(true);
        return reg;
    }

    private void bajaDeTransporte() {
        int op = 1;
        while (op == 1) {
            Consola.emitirMensaje("Ingrese el codigo de transporte: ");
            int cod = Consola.leerInt();
            Registro reg = obtenerTransporte(cod);
            if (reg != null) {
                Consola.emitirMensajeLN("Confirma baja? 1.SI 2.NO");
                int conf = Consola.leerInt();
                if (conf == 1) {
                    reg.setEstado(false);
                    archiTransp.modificarRegistro(reg);
                    Consola.emitirMensajeLN("Baja realizada.");
                }
            } else {
                Consola.emitirMensajeLN("Transporte no encontrado.");
            }
            Consola.emitirMensajeLN("Desea dar de baja otro transporte? 1.SI 2.NO");
            op = Consola.leerInt();
        }
    }

    private void modificarTransporte() {
        int op = 1;
        while (op == 1) {
            Consola.emitirMensaje("Codigo de transporte a modificar: ");
            int cod = Consola.leerInt();
            Registro reg = obtenerTransporte(cod);
            if (reg != null) {
                Transporte t = (Transporte) reg.getDatos();
                t.cargarDatos(2); // modificar datos excepto código
                archiTransp.modificarRegistro(reg);
                Consola.emitirMensajeLN("Transporte modificado.");
            } else {
                Consola.emitirMensajeLN("Transporte no encontrado.");
            }
            Consola.emitirMensajeLN("Desea modificar otro transporte? 1.SI 2.NO");
            op = Consola.leerInt();
        }
    }

    private Registro obtenerTransporte(int cod) {
        archiTransp.abrirParaLectura();
        archiTransp.irPrincipioArchivo();
        while (!archiTransp.eof()) {
            Registro reg = archiTransp.leerRegistro();
            if (reg.getEstado()) {
                Transporte t = (Transporte) reg.getDatos();
                if (t.getCodT() == cod) return reg;
            }
        }
        archiTransp.cerrarArchivo();
        return null;
    }

    public static Archivo getArchiTransp() {
        return archiTransp;
    }
}