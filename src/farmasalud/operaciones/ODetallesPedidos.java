/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ADetallePedidos;
import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetallePedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import farmasalud.atributosclases.ADetalleCotizacion;

/**
 *
 * @author Francisco Palacio
 */
public class ODetallesPedidos extends ConexionB implements IDetallePedidos {

    private ADetallePedidos detalles;
    private final Connection conexion;

    public ODetallesPedidos(Connection conexion) {
        this.conexion = conexion;
    }
    

    @Override
    public ArrayList<ADetallePedidos> obtenerTodos() {
        ArrayList<ADetallePedidos> arrayListDetallePedidos= new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM detallepedidos");
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                detalles= new ADetallePedidos();
                detalles.setIdProducto(resultSet.getInt("id_producto"));
                detalles.setCantidad(resultSet.getInt("cantidad"));
                detalles.setIdDetalle(resultSet.getInt("id_detallepedidos"));
                detalles.setCantidadEnvioInterno(resultSet.getInt("cantidadenviointerno"));
                detalles.setNumeroPedidos(resultSet.getInt("numeropedido"));
                arrayListDetallePedidos.add(detalles);
            }     
    
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetallePedidos;
    }

    @Override
    public ArrayList<ADetallePedidos> obtenerUno(Integer id) {
         ArrayList<ADetallePedidos> arrayListDetallePedidos= new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM detallepedidos WHERE id_detallepedidos=?");
            miStatement.setInt(1, id);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                detalles= new ADetallePedidos();
                detalles.setIdProducto(resultSet.getInt("id_producto"));
                detalles.setCantidad(resultSet.getInt("cantidad"));
                detalles.setIdDetalle(resultSet.getInt("id_detallepedidos"));
             detalles.setCantidadEnvioInterno(resultSet.getInt("cantidadenviointerno"));
                detalles.setNumeroPedidos(resultSet.getInt("numeropedido"));
                arrayListDetallePedidos.add(detalles);
            }     
    
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetallePedidos;
    }

    @Override
    public int insertar(ADetallePedidos a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ADetallePedidos a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ArrayList<ADetallePedidos> traerDetallesProductos(int nroPedido, String procedimiento) {
       ArrayList<ADetallePedidos> arrayListDetallePedido = new ArrayList<>();
        try {
            statement=conexion.prepareCall(procedimiento);
            statement.setInt(1, nroPedido);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                detalles = new ADetallePedidos();
                detalles.setIdDetalle(resultSet.getInt("ID"));
                detalles.setCantidad(resultSet.getInt("Cantidad"));
                detalles.setIdProducto(resultSet.getInt("Id_Producto"));
               detalles.setCantidadEnvioInterno(resultSet.getInt("cantidadenviointerno"));
                detalles.setNombreProducto(resultSet.getString("NombreComercial"));
                arrayListDetallePedido.add(detalles);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               this.cerrarProcedimiento();
            } catch (SQLException ex) {
                Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetallePedido;
    }

    @Override
    public ArrayList<ADetallePedidos> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetallePedidos> obtenerNombreComercialDeteallesProducto(String procedimiento) {
      ArrayList<ADetallePedidos> arrayListPedido = new ArrayList<>();
        try {
           statement=conexion.prepareCall(procedimiento);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                detalles = new ADetallePedidos();
                detalles.setNombreProducto(resultSet.getString("Nombre"));
                arrayListPedido.add(detalles);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          try {
              this.cerrarProcedimiento();
          } catch (SQLException ex) {
              Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              this.cerrarResultSet();
          } catch (SQLException ex) {
              Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
        return arrayListPedido;
    }

    @Override
    public ArrayList<ADetallePedidos> buscarEnDetallesProductos(String texto,String procedimiento) {
          ArrayList<ADetallePedidos> arrayListPedido = new ArrayList<>();
        try {
           statement=conexion.prepareCall(procedimiento);
          statement.setString(1, texto);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
              detalles = new ADetallePedidos();
               detalles.setNumeroPedidos(resultSet.getInt("NumeroPedido"));
                arrayListPedido.add(detalles);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          try {
              this.cerrarProcedimiento();
          } catch (SQLException ex) {
              Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              this.cerrarResultSet();
          } catch (SQLException ex) {
              Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
return arrayListPedido;
    }

   

    @Override
    public ArrayList<ADetallePedidos> procedimientoVerDetalleDeConsolidado(int nroProducto) {
         ArrayList<ADetallePedidos> arrayListDetallePedidos = new ArrayList<>();
        try {
            statement=conexion.prepareCall("call verDetalleConsolidado(?)");
            statement.setInt(1,nroProducto);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                detalles= new ADetallePedidos();
                 detalles.setIdDetalle(resultSet.getInt("idDetallePedidos"));
                 detalles.setNumeroPedidos(resultSet.getInt("numeroPedido"));
                arrayListDetallePedidos.add(detalles);
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
        return arrayListDetallePedidos;

    }

    @Override
    public ArrayList<ADetallePedidos> obtenerDetallesPorCabecera(int nroCabecera) {
        ArrayList<ADetallePedidos> arrayListDetallePedidos= new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM detallepedidos WHERE numeropedido=?");
            miStatement.setInt(1, nroCabecera);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                detalles= new ADetallePedidos();
                detalles.setIdProducto(resultSet.getInt("id_producto"));
                detalles.setCantidad(resultSet.getInt("cantidad"));
                detalles.setIdDetalle(resultSet.getInt("id_detallepedidos"));
                 detalles.setCantidadEnvioInterno(resultSet.getInt("cantidadenviointerno"));
                detalles.setNumeroPedidos(resultSet.getInt("numeropedido"));
                arrayListDetallePedidos.add(detalles);
            }     
    
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesPedidos.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListDetallePedidos;
    }

    @Override
    public void actualizar(ArrayList<ADetallePedidos> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
