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
public class TPG2_PROG_2025 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Archivo archiCond= new Archivo("CONDUCTORES.dat", new Conductor());
            GestorEmpresa.setArchiConduc(archiCond);
            GestorEmpresa.setReg(new Registro());
            
            Archivo archiTransp = new Archivo("Transporte.dat", new TransporteMercaderia());
            MenuTransporte.setArchiTransp(archiTransp);
            MenuTransporte.setTransporte(new TransporteMercaderia());
            MenuTransporte.setReg(new Registro());
            
            GestorEmpresa.menu();
        }catch(Exception e){
            Consola.emitirMensajeLN("Error al crear archivos: "+ e.getMessage());
            
        }
    }
    
}
