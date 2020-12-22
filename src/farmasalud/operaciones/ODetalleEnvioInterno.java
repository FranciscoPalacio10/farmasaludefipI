/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraEnvioInterno;
import farmasalud.atributosclases.ADetalleEnvioInterno;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetalleEnvioInterno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ODetalleEnvioInterno extends ConexionB implements IDetalleEnvioInterno {

    private ADetalleEnvioInterno obtenerDetalle;
    private Connection conexion;

    public ODetalleEnvioInterno(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<ADetalleEnvioInterno> obtenerTodos() {
        ArrayList<ADetalleEnvioInterno> arrayListDetalleEnvioInterno = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleenviointerno");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleEnvioInterno();
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetallenvio(resultSet.getInt("id_detallenvio"));
                obtenerDetalle.setIdDetallepedido(resultSet.getInt("id_detallepedido"));
                obtenerDetalle.setIdEnvioInterno(resultSet.getInt("id_detallepedido"));
                obtenerDetalle.setIdProducto(resultSet.getInt("id_producto"));
                arrayListDetalleEnvioInterno.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleEnvioInterno;
    }

    @Override
    public ArrayList<ADetalleEnvioInterno> obtenerUno(Integer id) {
        ArrayList<ADetalleEnvioInterno> arrayListDetalleEnvioInterno = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleenviointerno WHERE id_detallenvio=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleEnvioInterno();
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetallenvio(resultSet.getInt("id_detallenvio"));
                obtenerDetalle.setIdDetallepedido(resultSet.getInt("id_detallepedido"));
                obtenerDetalle.setIdEnvioInterno(resultSet.getInt("id_detallepedido"));
                obtenerDetalle.setIdProducto(resultSet.getInt("id_producto"));
                arrayListDetalleEnvioInterno.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleEnvioInterno;
    }

    @Override
    public int insertar(ADetalleEnvioInterno a) {
        int idDetalleEnvioInternoInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO detalleenviointerno(id_enviointerno, "
                    + "cantidad, id_producto, id_detallepedido) "
                    + "VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getIdEnvioInterno());
            miStatement.setInt(2, a.getCantidad());
            miStatement.setInt(3, a.getIdProducto());
            miStatement.setInt(4, a.getIdDetallepedido());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {
                idDetalleEnvioInternoInsertada = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idDetalleEnvioInternoInsertada;
    }

    @Override
    public void modificar(ADetalleEnvioInterno a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ADetalleEnvioInterno> lista) {
          lista.forEach((ADetalleEnvioInterno a) -> {
            try {
                miStatement = conexion.prepareStatement("UPDATE detalleenviointerno "
                        + "SET id_enviointerno=?,"
                        + "cantidad=?,id_producto=?,id_detallepedido=?WHERE id_detallenvio=?");
            miStatement.setInt(1, a.getIdEnvioInterno());
            miStatement.setInt(2, a.getCantidad());
            miStatement.setInt(3, a.getIdProducto());
            miStatement.setInt(4, a.getIdDetallepedido());
              miStatement.setInt(4, a.getIdDetallenvio());
                miStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    this.cerrarStament();
                    this.cerrarResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(ODetalleEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public ArrayList<ADetalleEnvioInterno> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
