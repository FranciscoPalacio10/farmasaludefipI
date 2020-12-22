/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ADetalleLote;
import farmasalud.atributosclases.AInvetario;
import farmasalud.atributosclases.ALoteProducto;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetalleLote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ODetalleLote extends ConexionB implements IDetalleLote{
private ADetalleLote traer;
    private final Connection conexion;

    public ODetalleLote (Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public ArrayList<ADetalleLote> obtenerTodos() {
           ArrayList<ADetalleLote> arrayDetalleLote = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  detallelote ");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ADetalleLote();
                traer.setCantidadVendida(resultSet.getInt("cantidadvendida"));
                traer.setEstado(resultSet.getString("estado"));
                traer.setIdDetalleLote(resultSet.getInt("id_detallelote"));
                traer.setIdLote(resultSet.getString("id_lote"));
                traer.setStock(resultSet.getInt("cantidad"));
                traer.setIdDetallePedido(resultSet.getInt("id_detallepedido"));
              
               arrayDetalleLote.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayDetalleLote;
    }

    @Override
    public ArrayList<ADetalleLote> obtenerUno(Integer id) {
         ArrayList<ADetalleLote> arrayDetalleLote = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  detallelote WHERE id_detallelote=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ADetalleLote();
                traer.setCantidadVendida(resultSet.getInt("cantidadvendida"));
                traer.setEstado(resultSet.getString("estado"));
                traer.setIdDetalleLote(resultSet.getInt("id_detallelote"));
                traer.setIdLote(resultSet.getString("id_lote"));
                traer.setStock(resultSet.getInt("cantidad"));
              traer.setIdDetallePedido(resultSet.getInt("id_detallepedido"));
               arrayDetalleLote.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayDetalleLote;
    
    
    }

    @Override
    public int insertar(ADetalleLote a) {
      int idDetalleLote = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO detallelote("
                    + "id_lote, cantidad, id_detallepedido, cantidadvendida, estado)"
                    + "VALUES (?,?,?,?,?) ", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setString(1,a.getIdLote());
            miStatement.setInt(2, a.getStock());
            miStatement.setInt(3, a.getIdDetallePedido());
             miStatement.setInt(4, a.getCantidadVendida());
            miStatement.setString(5, a.getEstado());
            miStatement.executeUpdate();
             resultSet = miStatement.getGeneratedKeys();
             while (resultSet.next()) {
                idDetalleLote = resultSet.getInt(1);
            }
            } catch (SQLException ex) {
            Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idDetalleLote;
    
    }

    @Override
    public void modificar(ADetalleLote a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ADetalleLote> lista) {
       lista.forEach((ADetalleLote a) -> {
            try {
                miStatement = conexion.prepareStatement(" UPDATE detallelote SET "
                        + "id_lote=?,cantidad=?,id_detallepedido=?,"
                        + "cantidadvendida=?,estado=? WHERE id_detallelote=?");   
              miStatement.setString(1,a.getIdLote());
            miStatement.setInt(2, a.getStock());
            miStatement.setInt(3, a.getIdDetallePedido());
             miStatement.setInt(4, a.getCantidadVendida());
            miStatement.setString(5, a.getEstado());
            miStatement.setString(5,a.getIdLote());
            miStatement.setInt(6,a.getIdDetalleLote());
                miStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    this.cerrarStament();
                    this.cerrarResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public ArrayList<ADetalleLote> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ADetalleLote> obtenerIDetalePedido(int idDetalle) {
       ArrayList<ADetalleLote> arrayDetalleLote = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  detallelote WHERE id_detallepedido=?");
            miStatement.setInt(1, idDetalle);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ADetalleLote();
                traer.setCantidadVendida(resultSet.getInt("cantidadvendida"));
                traer.setEstado(resultSet.getString("estado"));
                traer.setIdDetalleLote(resultSet.getInt("id_detallelote"));
                traer.setIdLote(resultSet.getString("id_lote"));
                traer.setStock(resultSet.getInt("cantidad"));
              traer.setIdDetallePedido(resultSet.getInt("id_detallepedido"));
               arrayDetalleLote.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarProcedimiento();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayDetalleLote;
    }

    @Override
    public List<AInvetario> procedimientoObtenerStock(String lote) {
       ArrayList<AInvetario> arrayDetalleLote = new ArrayList<>();
        AInvetario  traer;
        try {
            statement = conexion.prepareCall("{CALL obtenerInvetario(?)}");
            statement.setString(1, lote);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
           traer = new AInvetario();
                traer.setCantidad(resultSet.getInt("cantidad"));
                traer.setFechaVto(resultSet.getDate("fechaVTO"));
                traer.setSucursal(resultSet.getString("sucursal"));
                traer.setLote(resultSet.getString("lote"));
                traer.setIdProducto(resultSet.getInt("idProducto"));
               arrayDetalleLote.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayDetalleLote;
    }

    @Override
    public List<AInvetario> procedimientoObtenerStockXProducto(int idProducto) {
 ArrayList<AInvetario> arrayDetalleLote = new ArrayList<>();
        AInvetario  traer;
        try {
            statement = conexion.prepareCall("{CALL stockxproducto(?)}");
            statement.setInt(1, idProducto);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
           traer = new AInvetario();
                traer.setCantidad(resultSet.getInt("cantidad"));
                traer.setSucursal(resultSet.getString("sucursal"));
                traer.setIdProducto(resultSet.getInt("producto"));
               arrayDetalleLote.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleLote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayDetalleLote;
    }

    
    
}
