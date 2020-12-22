/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.ConsultasGenerales;
import farmasalud.conectarBD.ConexionB;
import interfaces.IObtenerEstados;
import java.sql.Connection;
import java.sql.SQLException;
import farmasalud.atributosclases.AEstados;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OEstadosXProceso extends ConexionB implements IObtenerEstados {
    private final Connection conexion;
    private AEstados cargar;

    
    
        public OEstadosXProceso(Connection conexion) {   
        this.conexion=conexion; 
                 }

    
    
    

  

    @Override
    public int insertar(AEstados a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(AEstados a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AEstados> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AEstados> obtenerUno(String id) {
         ArrayList<AEstados> estados = new ArrayList();
     try {
            statement=conexion.prepareCall("{call obtenerEstadoPorProceso(?)}");
            statement.setString(1,id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                cargar= new AEstados();
                cargar.setDescripcion(resultSet.getString("estados.descripcion"));
                estados.add(cargar);
                           }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
         try {
             this.cerrarProcedimiento();
         } catch (SQLException ex) {
             Logger.getLogger(OEstadosXProceso.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             this.cerrarResultSet();
         } catch (SQLException ex) {
             Logger.getLogger(OEstadosXProceso.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
        System.out.println(estados);
     return estados;
    }

    @Override
    public ArrayList<AEstados> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    @Override
    public void actualizar(ArrayList<AEstados> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
