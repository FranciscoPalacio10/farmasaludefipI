
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.conectarBD.ConexionB;
import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.pedidosSucursales.pintarCeldas;
import interfaces.IDetalleCotizaciones;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class ODetallesCotizaciones extends ConexionB implements IDetalleCotizaciones {
   
  private ADetalleCotizacion obtenerDetalle;  
  private Connection conexion;
    public ODetallesCotizaciones(Connection conexion) {
        this.conexion=conexion;
    }
      
    @Override
        public ArrayList<ADetalleCotizacion> devolverIDproductoConsolidado(){
            ArrayList<ADetalleCotizacion> arrayListDetalleConsolidado= new ArrayList<>();
            try {
            statement=conexion.prepareCall("{call devolverIDproductoConsolidado()}");
            resultSet=statement.executeQuery();
            while(resultSet.next()){
              obtenerDetalle= new ADetalleCotizacion();
              obtenerDetalle.setIdProducto(resultSet.getInt("Producto"));
                System.out.println(resultSet.getInt("Producto"));
              arrayListDetalleConsolidado.add(obtenerDetalle);
                  }   
             
          cerrarProcedimiento();
          cerrarResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        }  finally  {
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
             return  arrayListDetalleConsolidado;
         }
           

    @Override
    public ArrayList<ADetalleCotizacion> obtenerTodos() {
      ArrayList<ADetalleCotizacion> arrayListDetalleCotizacion = new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM detallecotizacion");
           resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                obtenerDetalle= new ADetalleCotizacion();
                obtenerDetalle.setIdProducto(resultSet.getInt("id_producto"));
                obtenerDetalle.setIdCotizacion(resultSet.getInt("id_cotizacion"));
                obtenerDetalle.setIdDetalleCotizacion(resultSet.getInt("id_detallecotizacion"));
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                arrayListDetalleCotizacion.add(obtenerDetalle);
            }     
    
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetalleCotizacion;      
    }



    @Override
    public int insertar(ADetalleCotizacion a) {
        int idDetalleCotizacionInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO detallecotizacion(id_cotizacion,cantidad,id_producto) "
                    + "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getIdCotizacion());
            miStatement.setInt(2, a.getCantidad());
            miStatement.setInt(3, a.getIdProducto());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {
                idDetalleCotizacionInsertada = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     return idDetalleCotizacionInsertada;
    }

    @Override
    public void modificar(ADetalleCotizacion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetalleCotizacion> obtenerUno(Integer id) {
      ArrayList<ADetalleCotizacion> arrayListDetalleCotizacion = new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM detallecotizacion WHERE id_cotizacion=?");
            miStatement.setInt(1, id);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                obtenerDetalle= new ADetalleCotizacion();
                obtenerDetalle.setIdProducto(resultSet.getInt("id_producto"));
                obtenerDetalle.setIdCotizacion(resultSet.getInt("id_cotizacion"));
                obtenerDetalle.setIdDetalleCotizacion(resultSet.getInt("id_detallecotizacion"));
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                arrayListDetalleCotizacion.add(obtenerDetalle);
            }     
    
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetalleCotizacion;        
    }

    @Override
    public ArrayList<ADetalleCotizacion> ejecutarProcedimientoAlmacenado(String procedimiento) {
   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Te
    }

   
 

    @Override
    public int obtenerIdDetalleCotizacion(int nroProducto) {
           int IdDetalleCotizacion = 0;
        try {
            statement=conexion.prepareCall("call obtenerIdDetalleCotizacion(?)");
            statement.setInt(1,nroProducto);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                IdDetalleCotizacion=resultSet.getInt("IddetalleCotizacion");
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
        return IdDetalleCotizacion;
    }

    @Override
    public ArrayList<Integer> obtenerIdProductos(int idDetalleCotizacion) {
       ArrayList<Integer> arrayListDetalleCotizacion = new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM detallecotizacion WHERE id_detallecotizacion=?");
            miStatement.setInt(1, idDetalleCotizacion);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                arrayListDetalleCotizacion.add(resultSet.getInt("id_producto"));
            }     
    
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetalleCotizacion;        
    }

    @Override
    public void actualizar(ArrayList<ADetalleCotizacion> lista) {
       lista.forEach((ADetalleCotizacion adc)->{
         try {
            miStatement = conexion.prepareStatement("UPDATE detallecotizacion SET cantidad = (?) WHERE id_detallecotizacion=(?)");
            miStatement.setInt(1, adc.getCantidad());
             System.out.println(adc.getIdDetalleCotizacion());
            miStatement.setInt(2, adc.getIdDetalleCotizacion());
            miStatement.executeUpdate();
          
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       });
      
    }

   
         }
    

