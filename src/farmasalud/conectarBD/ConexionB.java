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
public abstract class ConexionB {
 private Connection conn;
 protected PreparedStatement miStatement=null;
 protected  ResultSet resultSet=null;
 protected CallableStatement statement=null;
  
    protected void cerrarStament() throws SQLException{    
        if(miStatement!=null){
            miStatement.close();
            }
        }
  protected void cerrarProcedimiento() throws SQLException{    
        if(statement!=null){
              System.out.println("cerrando consulta");
            statement.close();
            }
        }
    public void desconectarBD() throws SQLException{
     if(conn!=null){
         System.out.println("cerrandoConexion");
         conn.close();
       }     
    }
    
    
    protected void cerrarResultSet() throws SQLException{
         if(resultSet!=null){  
               System.out.println("cerrando obtener resultados");
            resultSet.close();
    }
         
     }    
}
