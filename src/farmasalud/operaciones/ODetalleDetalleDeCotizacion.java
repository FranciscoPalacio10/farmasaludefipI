/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.conectarBD.ConexionB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.ADetalleDetalleDeCotizacion;
import interfaces.IDetalleDetalleCotizacion;
import java.sql.PreparedStatement;

/**
 *
 * @author Francisco Palacio
 */
 public class ODetalleDetalleDeCotizacion extends ConexionB implements IDetalleDetalleCotizacion {
private ADetalleDetalleDeCotizacion obtenerDetalle;  
    private final Connection conexion;
    public ODetalleDetalleDeCotizacion(Connection conexion) {   
        this.conexion=conexion; 
    }
    
    
    @Override
    public ArrayList<ADetalleDetalleDeCotizacion> obtenerTodos() {
          ArrayList<ADetalleDetalleDeCotizacion> arrayListDetalleDelDetalle = new ArrayList<>();
              
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM detalledeldetallecotizacion");
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                  obtenerDetalle=new ADetalleDetalleDeCotizacion();
                  obtenerDetalle.setIdDetalleCotizacion(resultSet.getInt("id_detallecotizacion"));
                  obtenerDetalle.setIdDetallePedido(resultSet.getInt("id_detallepedido"));
                  arrayListDetalleDelDetalle.add(obtenerDetalle);
                        }
              
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return arrayListDetalleDelDetalle;
    }

    @Override
    public ArrayList<ADetalleDetalleDeCotizacion> obtenerUno(Integer id) {
              ArrayList<ADetalleDetalleDeCotizacion> arrayListDetalleDelDetalle = new ArrayList<>();
              
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM detalledeldetallecotizacion WHERE id_detallecotizacion= ?");
            miStatement.setInt(1, id);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                  obtenerDetalle=new ADetalleDetalleDeCotizacion();
                  obtenerDetalle.setIdDetalleCotizacion(resultSet.getInt("id_detallecotizacion"));
                  obtenerDetalle.setIdDetallePedido(resultSet.getInt("id_detallepedido"));
                  arrayListDetalleDelDetalle.add(obtenerDetalle);
                        }
              
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return arrayListDetalleDelDetalle;
    }

    @Override
    public int insertar(ADetalleDetalleDeCotizacion a) {
       ArrayList<ADetalleDetalleDeCotizacion > arrayListCotizacion = new ArrayList<>();
        int idCotizacionInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO detalledeldetallecotizacion(id_detallecotizacion,"
                    + "id_detallepedido) "
                    + "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getIdDetalleCotizacion());
            miStatement.setInt(2, a.getIdDetallePedido());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {

                idCotizacionInsertada = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarProcedimiento();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idCotizacionInsertada;
    }

    @Override
    public void modificar(ADetalleDetalleDeCotizacion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetalleDetalleDeCotizacion> ejecutarProcedimientoAlmacenado(String procedimiento) {
   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   // @Override
   // public ArrayList<ADetalleDetalleDeCotizacion> procedimientoVerDetalleDeConsolidado(String procedimiento,int id) {
       //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       /*      ArrayList<ADetalleDetalleDeCotizacion> arrayListDetalleCotizacion = new ArrayList<>();
        try {
            statement=conexion.prepareCall("call verDetalleConsolidado(?)");
            statement.setInt(1, id);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                obtenerDetalle= new ADetalleDetalleDeCotizacion ();
                obtenerDetalle.setIdDetallePedido(resultSet.getInt("numeroPedido"));
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setSucursal(resultSet.getString("sucursal"));
                arrayListDetalleCotizacion.add(obtenerDetalle);
            }    
            
        } catch (SQLException ex) {
            Logger.getLogger(ADetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }   finally  {
           try {
               this.cerrarProcedimiento();
           } catch (SQLException ex) {
               Logger.getLogger(ADetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ADetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
           }
        } 
         return  arrayListDetalleCotizacion;
*/
    //}



    @Override
    public ArrayList<Integer> devolverIDproductoConsolidado() {
               ArrayList<Integer> arrayListID = new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT COUNT(d.id_detallecotizacion) as cantidad , p.id_producto as idProducto "
                    + "from detalledeldetallecotizacion d ,productos p, detallecotizacion dc "
                    + "WHERE d.id_detallecotizacion = dc.id_detallecotizacion "
                    + "and p.id_producto=dc.id_producto "
                    + "GROUP BY d.id_detallecotizacion HAVING cantidad > 1");
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                  arrayListID.add(resultSet.getInt("idProducto"));
                        }
              
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(ODetalleDetalleDeCotizacion.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return arrayListID;
    }

    @Override
    public void actualizar(ArrayList<ADetalleDetalleDeCotizacion> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
