/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpg2_prog_2025;

import datos.*;
import persistencia.*;
import static tpg2_prog_2025.MenuTransporte.getArchiTransp;

/**
 *
 * @author alvar
 */
public class GestorEmpresa {

    private static Archivo archiConduc;
    private int sueldoFijo = 400000;

    public GestorEmpresa() {
        try {
            archiConduc = new Archivo("CONDUCTORES.dat", new Conductor());
        } catch (ClassNotFoundException e) {
            Consola.emitirMensajeLN("Error al crear el archivo de conductores: " + e.getMessage());
            System.exit(1);
        }
    }

    public void showOptions() {
        Consola.emitirMensajeLN("====== Empresa Transporte ======");
        Consola.emitirMensajeLN("1. Cargar conductor");
        Consola.emitirMensajeLN("2. Actualizacion de transporte");
        Consola.emitirMensajeLN("3. Listado de sueldos");
        Consola.emitirMensajeLN("4. Listado de transporte por conductor");
        Consola.emitirMensajeLN("0. Salir");
    }

    public void menu() {
        int op;
        archiConduc.abrirParaLeerEscribir();

        do {
            showOptions();
            Consola.emitirMensajeLN("--> ");
            op = Consola.leerInt();

            switch (op) {
                case 1:
                    cargaDeConductor();
                    break;
                case 2:
                    new MenuTransporte().actualizacionTransporte();
                    break;
                case 3:
                    listadoDeSueldos();
                    break;
                case 4:
                    listarTransporteXConductor();
                    break;
            }
        } while (op != 0);

        archiConduc.cerrarArchivo();
    }

    private void cargaDeConductor() {
        try {
            do {
                Registro reg = leerDatosCond();
                archiConduc.cargarUnRegistro(reg);
            } while (Consola.confirmar());
        } catch (Exception e) {
            Consola.emitirMensajeLN("Error al cargar el archivo: " + e.getMessage());
        }
    }

    private Registro leerDatosCond() {
        Conductor conductor = new Conductor();
        long dni;

        do {
            Consola.emitirMensajeLN("DNI del conductor: ");
            dni = Consola.leerInteger();
            if (existeDni(dni) != null) {
                Consola.emitirMensajeLN("Conductor existente. Ingrese otro DNI.");
                dni = -1;
            }
        } while (dni < 0);

        conductor.agregarDni(dni);
        conductor.cargarDatos(1); // otros datos del conductor

        Registro reg = new Registro(conductor, (int) dni);
      //  reg.setEstado(true);
        return reg;
    }

    public static Registro existeDni(long dni) {
        System.out.println("gestorempresa. metodo existe dni");
        archiConduc.abrirParaLectura();
        archiConduc.irPrincipioArchivo();
        while (!archiConduc.eof()) {
            Registro reg = archiConduc.leerRegistro();
            if (reg.getEstado()) {
                Conductor c = (Conductor) reg.getDatos();
                if (c.getDni() == dni) {
                    System.out.println("-GESTOREMPRESA. existe el dni");
                    archiConduc.cerrarArchivo();
                    return reg;
                }
            }
        }
        return null;
    }

    public void listadoDeSueldos() {
        archiConduc.abrirParaLectura();
        archiConduc.irPrincipioArchivo();
        Archivo archiTransp = MenuTransporte.getArchiTransp();

        Consola.emitirMensajeLN(String.format("%-12s %-15s %-15s %-10s", "DNI", "Apellido", "Nombre", "Sueldo"));

        while (!archiConduc.eof()) {
            Registro regC = archiConduc.leerRegistro();
            if (regC.getEstado()) {
                Conductor c = (Conductor) regC.getDatos();
                double totalHoras = 0;
                double totalExtras = 0;

                archiTransp.irPrincipioArchivo();
                while (!archiTransp.eof()) {
                    Registro regT = archiTransp.leerRegistro();
                    if (regT.getEstado()) {
                        Transporte t = (Transporte) regT.getDatos();
                        if (t.getDniConductor() == c.getDni()) {
                            totalHoras += t.getHoras();
                            totalExtras += t.getExtra();
                        }
                    }
                }

                double sueldoFinal = sueldoFijo + (7500 * totalHoras) + totalExtras;
                Consola.emitirMensajeLN(String.format("%-12d %-15s %-15s %-10.2f",
                        c.getDni(), c.getApe_Nom(), sueldoFinal));
            }
        }
    }

    public void listarTransporteXConductor() {
        Archivo archiTransp = getArchiTransp();
        archiTransp.abrirParaLectura();
        archiTransp.irPrincipioArchivo();

        Consola.emitirMensajeLN("LISTADO TRANSPORTE POR CONDUCTOR");
        while (!archiTransp.eof()) {
            System.out.println("PRUEBA ENTRO AL WHILE DE LISTARXCONDUCTOR");
            Registro regT = archiTransp.leerRegistro();
            if (regT.getEstado()) {
                Transporte t = (Transporte) regT.getDatos();
                Registro regC = existeDni(t.getDniConductor());
                if (regC != null) {
                    Conductor c = (Conductor) regC.getDatos();
                    t.mostrarRegistro(1, true);
                    c.mostrarRegistro(1, true);
                    Consola.emitirMensajeLN("");
                }
            }
        }
        archiTransp.cerrarArchivo();
    }

    /*public static Archivo getArchiConduc() {
        return archiConduc;
    }

    
    public static Conductor getConductor() {
        return conductor;
    }

    public static Registro getReg() {
        return reg;
    }

    public static void setArchiConduc(Archivo archiConduc) {
        GestorEmpresa.archiConduc = archiConduc;
    }

    public static void setConductor(Conductor conductor) {
        GestorEmpresa.conductor = conductor;
    }

    public static void setReg(Registro reg) {
        GestorEmpresa.reg = reg;
    }*/
    /**
     * ******************GETTERS Y SETTERS***************************
     */
}
