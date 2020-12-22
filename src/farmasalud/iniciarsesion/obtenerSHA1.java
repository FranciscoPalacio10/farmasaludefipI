/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.iniciarsesion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class obtenerSHA1 {
  private static obtenerSHA1 obtenerInstancia;
  
    private obtenerSHA1() {
    
}
    
    public static obtenerSHA1 getInstancia(){
        if(obtenerInstancia==null){
            obtenerInstancia= new obtenerSHA1();
        }
       return obtenerInstancia;
        
    }
    
    
    public String obtenerSHA1(String pasword){
         String m = "";
    try {
            MessageDigest messageDigest2 = MessageDigest.getInstance("SHA"); // Inicializa SHA-1
           
            String caracter=pasword; // obtengo la pasword
            messageDigest2.update(caracter.getBytes());// pasa texto claro a resumen
            byte[] resumen2 = messageDigest2.digest(); //genero el resumen
                  
            for (int i = 0; i < resumen2.length; i++)
            {
               m += Integer.toHexString((resumen2[i] >> 4) & 0xf);
               m += Integer.toHexString(resumen2[i] & 0xf);
            }
            System.out.println("Resumen SHA-1: " + m);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FIniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
}
     
    
}
