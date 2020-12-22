/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ANombreGenerico;
import farmasalud.atributosclases.AParteDeRecepcion;
import farmasalud.conectarBD.ConexionB;
import interfaces.IParteDeRecepcion;
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
public class OParteDeRecepcion extends ConexionB implements IParteDeRecepcion {

    private AParteDeRecepcion traer;
    private final Connection conexion;

    public OParteDeRecepcion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<AParteDeRecepcion> obtenerTodos() {
        ArrayList<AParteDeRecepcion> arrayListParteDeRecepcion = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  partederecepcion");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AParteDeRecepcion();
                traer.setFechaRecepcion(resultSet.getDate("fecharecepcion"));
                traer.setIdParteRecepcion(resultSet.getInt("id_parterecepcion"));
                traer.setIdProveedor(resultSet.getInt("id_proveedor"));
                traer.setNumeroOc(resultSet.getInt("numero_oc"));
                traer.setNumeroRemito(resultSet.getInt("numeroremito"));
                traer.setObservaciones(resultSet.getString("observaciones"));
                traer.setEstado(resultSet.getString("estado"));
                traer.setIdUsuarioRecepcion(resultSet.getInt("usuario"));
                arrayListParteDeRecepcion.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(OParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListParteDeRecepcion;
    }

    @Override
    public ArrayList<AParteDeRecepcion> obtenerUno(Integer id) {
        ArrayList<AParteDeRecepcion> arrayListParteDeRecepcion = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  partederecepcion WHERE id_parterecepcion=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AParteDeRecepcion();
                traer.setFechaRecepcion(resultSet.getDate("fecharecepcion"));
                traer.setIdParteRecepcion(resultSet.getInt("id_parterecepcion"));
                traer.setIdProveedor(resultSet.getInt("id_proveedor"));
                traer.setNumeroOc(resultSet.getInt("numero_oc"));
                traer.setNumeroRemito(resultSet.getInt("numeroremito"));
                traer.setObservaciones(resultSet.getString("observaciones"));
                traer.setEstado(resultSet.getString("estado"));
                traer.setIdUsuarioRecepcion(resultSet.getInt("usuario"));
                arrayListParteDeRecepcion.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(OParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListParteDeRecepcion;
    }

    @Override
    public int insertar(AParteDeRecepcion a) {
        int idParteDeRecepcionInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO partederecepcion(numero_oc, numeroremito, "
                    + "id_proveedor, fecharecepcion, observaciones,estado,usuario) "
                    + "VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getNumeroOc());
            miStatement.setInt(2, a.getNumeroRemito());
            miStatement.setInt(3, a.getIdProveedor());
            miStatement.setDate(4, a.getFechaRecepcion());
            miStatement.setString(5, a.getObservaciones());
            miStatement.setString(6, a.getEstado());
            miStatement.setInt(7, a.getIdUsuarioRecepcion());

            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {
                idParteDeRecepcionInsertada = resultSet.getInt(1);
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
        return idParteDeRecepcionInsertada;
    }

    @Override
    public void modificar(AParteDeRecepcion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<AParteDeRecepcion> lista) {
        lista.forEach((AParteDeRecepcion adc) -> {
            try {
                miStatement = conexion.prepareStatement(" UPDATE partederecepcion SET numero_oc=?,"
                        + "numeroremito=?,id_proveedor=?,fecharecepcion=?,observaciones=?,estado=?,usuario=? WHERE id_parterecepcion=?");
                miStatement.setInt(1, adc.getNumeroOc());
                miStatement.setInt(2, adc.getNumeroRemito());
                miStatement.setInt(3, adc.getIdProveedor());
                miStatement.setDate(4, adc.getFechaRecepcion());
                miStatement.setString(5, adc.getObservaciones());
                miStatement.setString(6, adc.getEstado());
                miStatement.setInt(7, adc.getIdUsuarioRecepcion());
                miStatement.setInt(8, adc.getIdParteRecepcion());
            

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

    @Override
    public ArrayList<AParteDeRecepcion> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acutalizarEstado(String estado, int idCabecera) {
     try {
                miStatement = conexion.prepareStatement(" UPDATE partederecepcion SET estado= ? WHERE id_parterecepcion= ?");
                 miStatement.setString(1, estado);
                  miStatement.setInt(2, idCabecera);
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
        
    }

}
