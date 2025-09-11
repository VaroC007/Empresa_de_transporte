/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpg2_prog_2025;
import datos.*;
import persistencia.*;
/**
 *
 * @author alvar
 */
public class GestorEmpresa {
    private static Archivo cond;
    private static Archivo transp;
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
           //         actualizacionTransporte();
                    break;
                case 3:
           //         modificarDatos();
                    break;
                case 4:
           //         listar();
                    break;
                case 5:
           //         checkStock();
                    break;
            }
        } while (op != 0);
    }

    private static void cargaDeConductor() {
        cond.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerDatosCond();
                cond.cargarUnRegistro(reg);
            } while (Consola.confirmar());
        } catch (Exception e) {
            Consola.emitirMensajeLN("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        cond.cerrarArchivo();
    }
    
    private static Registro leerDatosCond() {
        Conductor datos = new Conductor();
        long dni;
        do {
            Consola.emitirMensajeLN("DNI del conductor: ");
            dni = Consola.leerInt();
            if (existeDni(dni)) {
                Consola.emitirMensajeLN("Dni ya existente.");
                dni = -1; //  Invalidar codigo si ya existe el conductor, 
                //  para que vuelva a pedir otro dni 
            }
        } while (dni < 0 || dni > datos.tamArchivo());
        datos.setDni(dni);
        datos.cargarDatos();
        Registro reg = new Registro(datos, datos.getNroOrden()); // Aqui es donde se indica que la clave principal es "dni"
        return reg;
    }
    //metodo que va a buscar los registros del conductor en el archivo de conductores para luego comparar dni
    private static boolean existeDni(long dni){
        cond.abrirParaLectura();
                    
        while (!cond.eof()){                                //me posicion al principio del archivo
            Registro reg = cond.leerRegistro();             //referencia reg para tener los datos del registro
            if (reg.getActivo()){                           //si esta activo realizo un casting y pregunto por dni
                Conductor c = (Conductor) reg.getDatos();
                if (c.getDni() == dni){
                    return true;
                }
            }
        }
        cond.cerrarArchivo();
        return false;
    }
    
    public static void actualizacionTransporte(){
        
        Consola.emitirMensajeLN("¿Que movimiento desea realizar?");
        Consola.emitirMensajeLN("1. Alta de transporte");
        Consola.emitirMensajeLN("2. Baja de transporte");
        Consola.emitirMensajeLN("3. Modificacion de transporte");
        int op = Consola.leerInt();
        switch (op){
            case 1:
                cargaTransporte();
                break;
            case 2:
                //bajaTransporte();
                break;
            case 3:
                //modificacionTransporte();
                break;
        }
    }
    
    private static void cargaTransporte(){
        transp.abrirParaLeerEscribir();
        try {
            do {
                Registro reg = leerDatosTransp();
                transp.cargarUnRegistro(reg);
            } while (Consola.confirmar());
        } catch (Exception e) {
            Consola.emitirMensajeLN("Error al cargar el archivo: " + e.getMessage());
            System.exit(1);
        }
        transp.cerrarArchivo();
    }
    
    //consulta sobre el dni del conductor y luego sobre el codigo del transporte hasta que ambos ingresos 
    //sean correctos (que no existan) para poder continuar con la carga de los demas datos
    private static Registro leerDatosTransp() {
        Consola.emitirMensajeLN("1. Transporte para personas");
        Consola.emitirMensajeLN("2. Transporte de mercaderia");
  
        int t = Consola.leerInt();
        Conductor datos = new Conductor();
        Registro reg;
        switch (t) {

            case 1:
                long dni;                              
                do {
                    Consola.emitirMensajeLN("DNI del conductor: ");
                    dni = Consola.leerInt();
                    if (!existeDni(dni)) {                   
                        Consola.emitirMensajeLN("Dni ya existente.");
                        dni = -1; //  Invalidar codigo si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (dni < 0 || dni > datos.tamArchivo());
                int cod;
                do {
                    Consola.emitirMensajeLN("Codigo del transporte: ");
                    cod = Consola.leerInt();
                    if (obtenerTransporte(cod) != null) {
                        Consola.emitirMensajeLN("Alta Existente.");
                        cod = -1; //  Invalidar codigo si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (cod < 0 || cod > tP.tamArchivo());
   //           TransportePersona tP = new TransportePersona();
            //  tP.setDni(dni);
            //  tP.setCodT(cod);
            //  tP.cargarDatos();
            //  reg = new Registro(tP, tp.getNroOrden);
                break;
            case 2:
                                               
                do {
                    Consola.emitirMensajeLN("DNI del conductor: ");
                    dni = Consola.leerInt();
                    if (existeDni(dni)) {                   
                        Consola.emitirMensajeLN("Dni ya existente.");
                        dni = -1; //  Invalidar codigo si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (dni < 0 || dni > datos.tamArchivo());
                //Transporte tM
                do {
                    Consola.emitirMensajeLN("Codigo del transporte: ");
                    cod = Consola.leerInt();
                    if (obtenerTransporte(cod) != null) {
                        Consola.emitirMensajeLN("Alta Existente.");
                        cod = -1; //  Invalidar codigo si ya existe el conductor, 
                        //  para que vuelva a pedir otro dni 
                    }
                } while (cod < 0 || cod > tM.tamArchivo());
                
            //  TransporteMercaderia tM = new TransporteMercaderia();
            //  tM.setDni(dni);
            //  tM.setCodT(cod);
            //  tM.cargarDatos();
            //  reg = new Registro(tM, tM.getNroOrden);
                break;
        }
        
        return reg;
       
    }
    
    //baja logica de los transporte
    private void bajaDeTransporte() {
        
        int op = -1;
        while (op != 2) {
            Consola.emitirMensaje("Ingrese el codigo de transporte: ");
            int cod = Consola.leerInt();        
            
            Registro reg = obtenerTransporte(cod);
            if(reg != null){
                Transporte t = (Transporte) reg.getDatos();
                //t.mostrarRegistro();
                Consola.emitirMensaje("¿Confirmar la baja? 1. Aceptar ** 2. Cancelar");
                int conf = Consola.leerInt();
                if(conf == 1){
                    transp.bajaRegistro(reg);
                }
            } else {
                Consola.emitirMensajeLN("No existe transporte con ese codigo");
            }
            
            Consola.emitirMensajeLN("Desea dar de baja otro Transporte? 1.SI 2.NO");
            op = Consola.leerInt();
        }

    }
    //metodo que me va a devolver un registro con un transporte dentro
    private static Registro obtenerTransporte(int cod) {
        transp.abrirParaLectura();
        while (!transp.eof()) {                                 //mientras no llegue al final del archivo
            Registro reg = transp.leerRegistro();               //paso los metadatos del registro a una referencia del mismo tipo
            if (reg.getActivo()) {
                Transporte t = (Transporte) reg.getDatos();     //si esta activa paso los datos del objeto que tiene registro a una referencia Transprote
                if (t.getCodT() == cod) {                       //pregunto por la igual de codigos
                    transp.cerrarArchivo();
                    return reg;
                }
            }
        }
        transp.cerrarArchivo();
        return null;
    }

    //modifica los datos del transporte salvo el codigo del transporte
    private static void modificarTransporte(){
        int op = -1;
        while (op != 0){
            Consola.emitirMensaje("Codigo de transporte a modificar: ");
            int cod = Consola.leerInt();
            Registro reg = obtenerTransporte(cod);
            if (reg != null) {
                Transporte aux = reg.getDatos();
                
                aux.mostrarDatos();
                
                if (aux instanceof TransportePersona){
                    TransportePersona t = (TransportePersona) aux;
                    Consola.emitirMensajeLN("Modificar transporte de personas: ");
                    t.cargarDatos();
                    reg.setDatos(t);
                } else if (aux instanceof TransporteMercaderia){
                    TransporteMercaderia t = (TransporteMercaderia) aux;
                    Consola.emitirMensajeLN("Modificar transporte de mercaderia: ");
                    t.cargarDatos();
                    reg.setDatos(t);
                }
                
                transp.abrirParaLeerEscribir();
                transp.grabarRegistro(reg);
                transp.cerrarArchivo();
                Consola.emitirMensajeLN("Modificaciones Hechas !!");
            } else {
                Consola.emitirMensajeLN("No existe transporte con ese codigo");
            }


            Consola.emitirMensajeLN("Desea modificar otro Transporte? 1.SI 2.NO");
            op = Consola.leerInt();
        }
        
    }
    
    
    
    
}
