package tpg2_prog_2025;

/**
 * Clase para facilitar operaciones de muestra por pantalla en consola estándar
 */

public class Emisor {
    
    /**
     * Todos los métodos de esta clase son estáticos, y por lo tanto pueden ser
     * invocados sin tener que crear objetos de la clase. Es suficiente con
     * nombrar la clase al invocar el método: int x = Emisor.readInt();
     */

    public static String ajustarString(String cad, int longitud) {
        if (cad.length() > longitud) {
            cad = cad.substring(0, longitud);
        } else {
            for (int i = cad.length(); i < longitud; i++) {
                cad = cad + " ";
            }
        }
        return cad;
    }
    
    /**
     * Este metodo acomoda un String para que se vea como un titulo.
     * Ejemplo: Emisor.titulo("12345",5,5) 
     *          Ouput: "-- 12345 --"; 
     *          Emisor.titulo("12345",10,5)
     *          Ouput: "------- 12345 -------"
     * 
     * @param sTitle String que representa un titulo.
     * @param iDes Indica cuanto se tiene que desplazar la cadena t respecto de su centro. 
     * @param iLong Longitud maxima del titulo.
     * @return sTitle
     */
    public static String titulo(String sTitle, int iDes, int iLong) {

        String cad1 = "";
        int iMedio=0;
        
        if (iDes>=iLong){
            iMedio = (int)(sTitle.length() / 2);
        } else {
            iMedio = (int)(iLong/2);
        }
        
        for (int i = iMedio; i < iDes-1; i++) {
            cad1 = cad1 + "-";
        }

        sTitle = cad1+" "+sTitle+" "+cad1;
        
        return sTitle;
    }
    
    /**
     * Este metodo acomoda un String para que se vea como un titulo.
     * Ejemplo: Emisor.titulo("12345",5,5,'>','<') 
     *          Ouput: ">> 12345 <<"; 
     *          Emisor.titulo("12345",10,5)
     *          Ouput: ">>>>>>> 12345 <<<<<<<"
     * 
     * @param sTitle String que representa un titulo.
     * @param iDes Indica cuanto se tiene que desplazar la cadena t respecto de su centro. 
     * @param iLong Longitud maxima del titulo.
     * @param c1 es el caracter para dar formato al titulo del lado izquierdo
     * @param c2 es el caracter para dar formato al titulo del lado derecho
     * @return sTitle
     */
    public static String titulo(String sTitle, int iDes, int iLong, char c1, char c2) {

        String cad1 = "";
        String cad2 = "";
        int iMedio=0;
        
        if (iDes>=iLong){
            iMedio = (int)(sTitle.length() / 2);
        } else {
            iMedio = (int)(iLong/2);
        }
        
        for (int i = iMedio; i < iDes-1; i++) {
            cad1 = cad1 + c1;
        }
        
        for (int i = iMedio; i < iDes-1; i++) {
            cad2 = cad2 + c2;
        }

        sTitle = cad1+" "+sTitle+" "+cad2;
        
        return sTitle;
    }
}