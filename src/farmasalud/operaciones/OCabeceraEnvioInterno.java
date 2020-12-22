/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraEnvioInterno;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICabeceraEnvioInterno;
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
public class OCabeceraEnvioInterno extends ConexionB implements ICabeceraEnvioInterno {

    private ACabeceraEnvioInterno obtenerDetalle;
    private Connection conexion;

    public OCabeceraEnvioInterno(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<ACabeceraEnvioInterno> obtenerTodos() {
        ArrayList<ACabeceraEnvioInterno> arrayListCabeceraEnvioInterno = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM enviointerno");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ACabeceraEnvioInterno();
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                obtenerDetalle.setFechaenvio(resultSet.getDate("fechaenvio"));
                obtenerDetalle.setIdEnvioInterno(resultSet.getInt("id_enviointerno"));
                obtenerDetalle.setNumeroPedido(resultSet.getInt("numeropedido"));
                arrayListCabeceraEnvioInterno.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCabeceraEnvioInterno;
    }

    @Override
    public ArrayList<ACabeceraEnvioInterno> obtenerUno(Integer id) {
        ArrayList<ACabeceraEnvioInterno> arrayListCabeceraEnvioInterno = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM enviointerno WHERE id_enviointerno=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ACabeceraEnvioInterno();
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                obtenerDetalle.setFechaenvio(resultSet.getDate("fechaenvio"));
                obtenerDetalle.setIdEnvioInterno(resultSet.getInt("id_enviointerno"));
                obtenerDetalle.setNumeroPedido(resultSet.getInt("numeropedido"));
                arrayListCabeceraEnvioInterno.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCabeceraEnvioInterno;
    }

    @Override
    public int insertar(ACabeceraEnvioInterno a) {
        int idCabeceraEnvioInternoInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO enviointerno(numeropedido, fechaenvio, estado) "
                    + "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getNumeroPedido());
            miStatement.setDate(2, a.getFechaenvio());
            miStatement.setString(3, a.getEstado());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {
                idCabeceraEnvioInternoInsertada = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idCabeceraEnvioInternoInsertada;
    }

    @Override
    public void modificar(ACabeceraEnvioInterno a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ACabeceraEnvioInterno> lista) {
        lista.forEach((ACabeceraEnvioInterno a) -> {
            try {
                miStatement = conexion.prepareStatement("UPDATE enviointerno SET id_enviointerno=?,"
                        + "numeropedido=?,fechaenvio=?,"
                        + "estado=? WHERE id_enviointerno=?");
                miStatement.setInt(1, a.getNumeroPedido());
                miStatement.setDate(2, a.getFechaenvio());
                miStatement.setString(3, a.getEstado());
                miStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    this.cerrarStament();
                    this.cerrarResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(OCabeceraEnvioInterno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public ArrayList<ACabeceraEnvioInterno> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
