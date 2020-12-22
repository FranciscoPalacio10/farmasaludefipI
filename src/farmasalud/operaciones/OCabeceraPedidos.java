/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import ds.desktop.notify.DesktopNotify;
import farmasalud.ConsultasGenerales;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICabeceraPedidos;
import farmasalud.atributosclases.ACabeceraPedidos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
 
/**
 *
 * @author Francisco Palacio
 */
public class OCabeceraPedidos extends ConexionB implements ICabeceraPedidos  {
  
    private final Connection conexion;
    public OCabeceraPedidos(Connection conexion) {   
        this.conexion=conexion; 
    }


    @Override
    public  ArrayList<ACabeceraPedidos> obtenerUltimosPedidos(String estado){
     ArrayList<ACabeceraPedidos> arrayListPedidos = new ArrayList<>();
        try {
            statement=conexion.prepareCall("{call traerUltimosPedidos(?)}");
            statement.setString(1,estado);
            resultSet=statement.executeQuery();
              while(resultSet.next()){
            ACabeceraPedidos pedidos=new ACabeceraPedidos();
                pedidos.setEstado(resultSet.getString("estado"));
                pedidos.setNumeroPedido(resultSet.getInt("numeropedido"));
                pedidos.setFechaPedido(resultSet.getDate("fecha"));
                pedidos.setId_Sucursal(resultSet.getString("id_sucursal"));
                arrayListPedidos.add(pedidos);
            }
                } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarProcedimiento();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListPedidos;
    }
   

    @Override
    public ArrayList<ACabeceraPedidos> obtenerTodos() {
         ArrayList<ACabeceraPedidos> arrayListPedidos = new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM pedidos,usuario,estados"
                    + " WHERE pedidos.id_usuario=usuario.id_usuario "
                    + "and pedidos.estado = estados.estado ORDER BY pedidos.numeropedido");
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
               ACabeceraPedidos pedidos=new ACabeceraPedidos();
                    pedidos.setNumeroPedido(resultSet.getInt("pedidos.numeropedido"));
                    pedidos.setFechaPedido(resultSet.getDate("pedidos.fecha"));
                    pedidos.setLugarDeEntrega(resultSet.getString("pedidos.lugardeentrega"));
                    pedidos.setUsuario(resultSet.getString("usuario.usuario"));
                    pedidos.setId_usuario(resultSet.getInt("usuario.id_usuario"));
                    pedidos.setEstado(resultSet.getString("estados.descripcion"));
                    pedidos.setId_Sucursal(resultSet.getString("usuario.id_sucursal"));
                     arrayListPedidos.add(pedidos);
                        }
              
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return arrayListPedidos;
    }

    @Override
    public ArrayList<ACabeceraPedidos> obtenerUno(Integer id) {
               ArrayList<ACabeceraPedidos> arrayListPedidos = new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM pedidos where numeropedido=?");
            miStatement.setInt(1, id);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                ACabeceraPedidos   pedidos=new ACabeceraPedidos();
                    pedidos.setNumeroPedido(resultSet.getInt("numeropedido"));
                    pedidos.setFechaPedido(resultSet.getDate("fecha"));
                    pedidos.setLugarDeEntrega(resultSet.getString("lugardeentrega"));
                    pedidos.setId_usuario(resultSet.getInt("id_usuario"));
                    pedidos.setEstado(resultSet.getString("estado"));
                    pedidos.setFechaPedido(resultSet.getDate("fecha"));
                     arrayListPedidos.add(pedidos);
                        }
              
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return arrayListPedidos;
    }

    @Override
    public int insertar(ACabeceraPedidos a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ACabeceraPedidos a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ACabeceraPedidos> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    @Override
    public void actulizarEstado(List<Integer> cabeceraEditarEstado,String estado) {
             cabeceraEditarEstado.stream().distinct().forEach((Integer cabecera)->{
                 try {
                     miStatement=conexion.prepareStatement("UPDATE pedidos SET estado=? WHERE numeropedido=?");
                    miStatement.setString(1, estado);
                    miStatement.setInt(2,cabecera);
                     miStatement.executeUpdate();
                 } catch (SQLException ex) {
                     Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
                 } finally{
             try {
                 this.cerrarResultSet();
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
      }
             });
                 
    

}
    @Override
    public ArrayList<ACabeceraPedidos> obtenerPedidosXEstado(String estado) {
                 ArrayList<ACabeceraPedidos> arrayListPedidos = new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM pedidos where estado=?");
            miStatement.setString(1, estado);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
             ACabeceraPedidos  pedidos=new ACabeceraPedidos();
                    pedidos.setNumeroPedido(resultSet.getInt("numeropedido"));
                    pedidos.setFechaPedido(resultSet.getDate("fecha"));
                    pedidos.setLugarDeEntrega(resultSet.getString("lugardeentrega"));
                    pedidos.setId_usuario(resultSet.getInt("id_usuario"));
                    pedidos.setEstado(resultSet.getString("estado"));
                     arrayListPedidos.add(pedidos);
                        }
              
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
             try {
                 this.cerrarResultSet();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                 this.cerrarStament();
             } catch (SQLException ex) {
                 Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        return arrayListPedidos;
    }

    @Override
    public void actualizar(ArrayList<ACabeceraPedidos> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
  
}
