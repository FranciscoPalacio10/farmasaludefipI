/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ADetalleConsolidado;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetalleConsolidado;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ODetallesConsolidados extends ConexionB implements IDetalleConsolidado {

    private Connection conexion;
   private ADetalleConsolidado obtenerDetalle;
   private OManager traer;
    /*
    ArrayList<ADetalleConsolidado> arrayListDetalleConsolidado = new ArrayList<>();
       arrayListDetalleConsolidado.addAll(obtenerDetallesConsolidadosNoFarma(nroPedido));
       arrayListDetalleConsolidado.addAll(obtenerDetallesConsolidadosFarma(nroPedido));
   */
    public ODetallesConsolidados(Connection conexion) {
            this.conexion=conexion;
    }
  
   
    
    private ArrayList<ADetalleConsolidado> obtenerDetallesConsolidadosNoFarma(int nroPedido){
        ArrayList<ADetalleConsolidado> arrayListDetalleConsolidado = new ArrayList<>();
        try {
            statement=conexion.prepareCall("call consolidarDetallesCotizacionNoFarma(?)");
            statement.setInt(1, nroPedido);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                obtenerDetalle= new ADetalleConsolidado();
                obtenerDetalle.setIdProducto(resultSet.getInt("Id"));
                obtenerDetalle.setMedida(resultSet.getInt("Medida"));
                obtenerDetalle.setTotal(resultSet.getInt("Total"));
                obtenerDetalle.setNombreProducto(resultSet.getInt("Nombre"));
               arrayListDetalleConsolidado.add(obtenerDetalle);
            }     
         
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarProcedimiento();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetalleConsolidado;
        
    }
    private ArrayList<ADetalleConsolidado> obtenerDetallesConsolidadosFarma(int nroPedido){
        ArrayList<ADetalleConsolidado> arrayListDetalleConsolidado = new ArrayList<>();
        try {
            statement=conexion.prepareCall("call consolidarDetallesCotizacionVademuc(?)");
             statement.setInt(1, nroPedido);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                obtenerDetalle= new ADetalleConsolidado();
                obtenerDetalle.setIdProducto(resultSet.getInt("Id"));
                obtenerDetalle.setMedida(resultSet.getInt("Medida"));
                obtenerDetalle.setTotal(resultSet.getInt("Total"));
                obtenerDetalle.setNombreProducto(resultSet.getInt("Nombre"));
               arrayListDetalleConsolidado.add(obtenerDetalle);
            }     
         
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarProcedimiento();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetalleConsolidado;
        
    }

    @Override
    public ArrayList<ADetalleConsolidado> obtenerDetallesConsolidados(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
