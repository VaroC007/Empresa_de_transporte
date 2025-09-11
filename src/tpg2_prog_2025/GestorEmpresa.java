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
    private static Conductor conductor;
    private static Registro reg;
    private static final int LONG = 16;
    
    public static void showOptions() {
        Consola.emitirMensajeLN(Emisor.titulo("Empresa Transporte", LONG * 3, LONG));
        Consola.emitirMensajeLN("1. Cargar conductor");
        Consola.emitirMensajeLN("2. Actualizacion de transporte");
        Consola.emitirMensajeLN("3. Listado de sueldos");
        Consola.emitirMensajeLN("4. Listado de transporte por conductor");
        Consola.emitirMensajeLN("0. Salir");
    }
    
    public static void menu() {
        /*try {
            cond = new Archivo("CONDUCTORES.dat", new Conductores());
        } catch (ClassNotFoundException e) {
            Consola.emitirMensajeLN("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }*/
        int op;

        do {

            showOptions();
            Consola.emitirMensajeLN("--> ");
            op = Consola.leerInt();
            switch (op) {
                case 1:
                    cargaDeConductor();
                    break;
                case 2:
                    MenuTransporte.actualizacionTransporte();
                    break;
                case 3:
           //         listadoDeSueldo();
                    break;
                case 4:
           //         listarTranspXConductor();
                    break;
                case 5:
           //         checkStock();
                    break;
            }
        } while (op != 0);
    }

    private static void cargaDeConductor() {
        archiConduc.abrirParaLeerEscribir();
        try {
            do {
                reg = leerDatosCond();
                archiConduc.cargarUnRegistro(reg);
            } while (Consola.confirmar());
        } catch (Exception e) {
            Consola.emitirMensajeLN("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        archiConduc.cerrarArchivo();
    }
    
    private static Registro leerDatosCond() {
        Conductor datos = new Conductor();
        long dni;
        do {
            Consola.emitirMensajeLN("DNI del conductor: ");
            dni = Consola.leerInt();
            if (existeDni(dni) != null) {
                Consola.emitirMensajeLN("Dni ya existente.");
                dni = -1; //  Invalidar codigo si ya existe el conductor, 
                //  para que vuelva a pedir otro dni 
            }
        } while (dni < 0 );
        datos.setDni(dni);
        datos.cargarDatos();
        Registro aux = new Registro(datos, (int)datos.getNroOrden()); // Aqui es donde se indica que la clave principal es "dni"
        return aux;
    }
    //metodo que va a buscar los registros del conductor en el archivo de conductores para luego comparar dni
    public static Registro existeDni(long dni){
        archiConduc.abrirParaLectura();
                    
        while (!archiConduc.eof()){                                //me posicion al principio del archivo
            Registro aux = archiConduc.leerRegistro();             //referencia reg para tener los datos del registro
            if (aux.getEstado()){                           //si esta activo realizo un casting y pregunto por dni
                Conductor c = (Conductor) aux.getDatos();
                if (c.getDni() == dni){
                    return aux;
                }
            }
        }
        archiConduc.cerrarArchivo();
        return null;
    }
    
    
    public static void listarTransporteXConductor(){
        Archivo auxA = getArchiTransp();
        auxA.abrirParaLectura();
        auxA.irPrincipioArchivo();
        Transporte auxT;
        while(!auxA.eof()){
            Registro auxR = auxA.leerRegistro();
            if
            if(auxR.getEstado()){
                auxR.mostrarRegistro(LONG, true);
                archiConduc.abrirParaLectura();
                archiConduc.irPrincipioArchivo();
                while(!archiConduc.eof()){
                    reg = archiConduc.leerRegistro();
                    if()
                }
            }
            
        }
    }
    
    
    
    
    
}
