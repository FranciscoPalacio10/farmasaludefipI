/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.conectarBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ConexionBD {
private static ConexionBD instancia;
private Connection conn;
private final String driver= "com.mysql.cj.jdbc.Driver";
private final  String user="root";
private final String pasword=""; 
private final String url="jdbc:mysql://localhost:3307/bd_farmasauld?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";   
 protected PreparedStatement miStatement=null;
 protected  ResultSet resultSet=null;
 protected CallableStatement statement=null;  
private ConexionBD() {
        try {
            Class.forName(driver);
            try {
                conn=DriverManager.getConnection(url,user,pasword);
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("conectado...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public static ConexionBD obtenerIntancia(){
      if(instancia==null){
          instancia= new ConexionBD();
      }
       return instancia;      
    }
    
}
