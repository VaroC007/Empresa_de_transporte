/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpg2_prog_2025;

import datos.*;
import persistencia.*;
import tpg2_prog_2025.GestorEmpresa.*;
import static tpg2_prog_2025.GestorEmpresa.existeDni;

/**
 *
 * @author alvar
 */
public class MenuTransporte {
    private static Archivo archiTransp;
    private static Registro reg; 
    private static Transporte transporte;
    
    
    MenuTransporte(){
        try{
            setArchiTransp(new Archivo("TRANSPORTE.dat", new TransporteMercaderia()));
        } catch(ClassNotFoundException e){
            Consola.emitirMensajeLN("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
        setReg(new Registro());
        setTransporte(new TransporteMercaderia());              //utilizamos la clase con mayor bytes tiene para que los bytes no se "pisen" en el archivo
        getReg().setDatos(getTransporte());
    }
    
    
    public static void mostrarMenuTransporte(){
        Consola.emitirMensajeLN("-ACTUALIZACION DE TRANSPORTE-");
        Consola.emitirMensajeLN("1. Alta de transporte");
        Consola.emitirMensajeLN("2. Baja de transporte");
        Consola.emitirMensajeLN("3. Modificacion de transporte");
        Consola.emitirMensajeLN("0. Salir");
        Consola.emitirMensaje("Elija una opcion: ");
    }
    
  /*  public static void crearArchivoTransporte(){
        try{
            setArchiTransp(new Archivo("C:\\Users\\alvar\\OneDrive\\Escritorio\\2025 PROGRAMACION III\\PROGIII_TPE1\\Transporte.dat", new TransporteMercaderia()));
        }catch(ClassNotFoundException e){
            Consola.emitirMensajeLN("Error al crear los descriptores de archivos: " + e.getMessage());
            System.exit(1);
        }
    }*/
    public static void actualizacionTransporte(){
        
        int op ;
        MenuTransporte mT = new MenuTransporte();
        getArchiTransp().abrirParaLeerEscribir();
        long cuanto = getArchiTransp().cantidadRegistros();
        getArchiTransp().cerrarArchivo();
        if (cuanto == 0) {
            getArchiTransp().crearArchivoVacio(new Registro(getTransporte(), 0));
            do {
                mostrarMenuTransporte();
                op = Consola.leerInt();
                switch (op) {
                    case 1:
                        Consola.emitirMensajeLN("-- Ingrese los datos del transporte --");
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
        
        }
       
    }
    
    private static void cargaTransporte(){
        archiTransp.abrirParaLeerEscribir();
        try {
            do {
                reg = leerDatosTransp();
                archiTransp.cargarUnRegistro(getReg());
            } while (Consola.confirmar());
        } catch (Exception e) {
            Consola.emitirMensajeLN("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        archiTransp.cerrarArchivo();
    }
    
 
    private static Registro leerDatosTransp() {
        Consola.emitirMensajeLN("1. Transporte para personas");
        Consola.emitirMensajeLN("2. Transporte de mercaderia");
        Consola.emitirMensaje("Elija una opcion: ");
        int t = Consola.leerInt();
        Transporte tt;
        
        Registro aux = null;
        
        switch (t) {

            case 1:
                long dni;
                do {
                    Consola.emitirMensajeLN("DNI del conductor: ");
                    dni = Consola.leerInt();
                    if (existeDni(dni) == null) {
                        Consola.emitirMensajeLN("Dni no existente.");
                        dni = -1; //  Invalidar dni si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (dni < 0 && dni > 100);
                int cod;
              
                do {
                    Consola.emitirMensajeLN("Codigo del transporte: ");
                    cod = Consola.leerInt();
                    if (obtenerTransporte(cod) != null) {
                        Consola.emitirMensajeLN("Alta Existente.");
                        cod = -1; //  Invalidar codigo si ya existe el conductor, 
                        //  para que vuelva a pedir otro cod 
                    }
                } while (cod < 0 || cod > 100);
              
                /////
                tt = new TransportePersonas();
                tt.agregarCod(cod);
                tt.agregarDni(dni);
                tt.cargarDatos(1);
                
      
                aux = new Registro(tt, tt.getCodT());
                aux.setEstado(true);
                
                break;
            case 2:
                 
                do {
                    Consola.emitirMensajeLN("DNI del conductor: ");
                    dni = Consola.leerInt();
                    if (existeDni(dni) == null) {
                        Consola.emitirMensajeLN("Dni no existente.");
                        dni = -1; //  Invalidar dni si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (dni < 0 && dni > 100);
                do {
                    Consola.emitirMensajeLN("Codigo del transporte: ");
                    cod = Consola.leerInt();
                    if (obtenerTransporte(cod) != null) {
                        Consola.emitirMensajeLN("Alta Existente.");
                        cod = -1; //  Invalidar codigo si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (cod < 0 || cod > 100);

                tt = new TransporteMercaderia();
                tt.agregarCod(cod);
                tt.agregarDni(dni);
                tt.cargarDatos(1);
                /////
          
                aux = new Registro(tt, tt.getCodT());
                break;
        }

        return aux;

    }
    
    //baja logica de los transporte
    private static void bajaDeTransporte() {
        
        int op = -1;
        while (op != 2) {
            Consola.emitirMensaje("Ingrese el codigo de transporte: ");
            
            ////
            getTransporte().cargarCodT();                               //carga del codigo 
            getReg().cargarNroOrden(getTransporte().getCodT());         //se genera el numero de orden a partir de codigo
            getArchiTransp().bajaRegistro(getReg());                    //busqueda interna y baja del registro (false)
            ////
            Consola.emitirMensajeLN("Desea dar de baja otro Transporte? 1.SI 2.NO");
            op = Consola.leerInt();
        }

    }
    //metodo que me va a devolver un registro con un transporte dentro
    private static Registro obtenerTransporte(int cod) {
        archiTransp.abrirParaLectura();
        archiTransp.irPrincipioArchivo();
        while (!archiTransp.eof()) {                                 //mientras no llegue al final del archivo
            Registro aux = archiTransp.leerRegistro();               //paso los metadatos del registro a una referencia del mismo tipo
            if (aux.getEstado()) {
                Transporte t = (Transporte) aux.getDatos();     //si esta activa paso los datos del objeto que tiene registro a una referencia Transprote
                if (t instanceof TransporteMercaderia) {
                    TransporteMercaderia auxtm = (TransporteMercaderia) t;
                    if (auxtm.getCodT() == cod) {
                        archiTransp.cerrarArchivo();
                        return aux;
                    }
                } else if (t instanceof TransportePersonas) {
                    TransportePersonas auxtp = (TransportePersonas) t;
                    if (auxtp.getCodT() == cod) {
                        archiTransp.cerrarArchivo();
                        return aux;
                    }
                }

            }
        }
        archiTransp.cerrarArchivo();
        return null;
    }

    //modifica los datos del transporte salvo el codigo del transporte
    private static void modificarTransporte(){
        int op = -1;
        while (op != 0){
            Consola.emitirMensaje("Codigo de transporte a modificar: ");
            
            ////
            getTransporte().cargarCodT();
            getReg().cargarNroOrden(getTransporte().getCodT());
            
            getArchiTransp().modificarRegistro(getReg());
            
            Consola.emitirMensajeLN("Desea modificar otro Transporte? 1.SI 2.NO");
            op = Consola.leerInt();
        }
        
    }
//--------------------------------------------------------------------
    public static Archivo getArchiTransp() {
        return archiTransp;
    }

    public static Registro getReg() {
        return reg;
    }

    public static Transporte getTransporte() {
        return transporte;
    }

    public static void setArchiTransp(Archivo archiTransp) {
        MenuTransporte.archiTransp = archiTransp;
    }

    public static void setReg(Registro reg) {
        MenuTransporte.reg = reg;
    }

    public static void setTransporte(Transporte transporte) {
        MenuTransporte.transporte = transporte;
    }
    
    
}
