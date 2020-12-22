/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ADetalleParteDeRecepcion;
import farmasalud.atributosclases.AParteDeRecepcion;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetalleParteDeRecepcion;
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
public class ODetalleParteDeRecepcion extends ConexionB implements IDetalleParteDeRecepcion {

    private Connection conexion;
    private ADetalleParteDeRecepcion traer;

    public ODetalleParteDeRecepcion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<ADetalleParteDeRecepcion> obtenerTodos() {
        ArrayList<ADetalleParteDeRecepcion> arrayDetalleParteDeRecepcion = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  detalleparterecepcion");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
               traer = new ADetalleParteDeRecepcion();
                traer.setCantidad(resultSet.getInt("cantidad"));
               traer.setCantidadRechazada(resultSet.getInt("cantidadrechazada"));
                traer.setCantidadAceptada(resultSet.getInt("cantidadaceptada"));
                traer.setCantidadRecibida(resultSet.getInt("cantidadrecibida"));
                traer.setDetalleOrdenDeCompra(resultSet.getInt("id_detalleordendecompra"));
                traer.setIdDetalleParteRecepcion(resultSet.getInt("id_detalleparterecepcion"));
                traer.setiDPartederecepcion(resultSet.getInt("id_partederecepcion"));
                traer.setIdProducto(resultSet.getInt("id_producto"));
                traer.setiDPartederecepcion(resultSet.getInt("idusuarioverificacion"));
                arrayDetalleParteDeRecepcion.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayDetalleParteDeRecepcion;
    }

    @Override
    public ArrayList<ADetalleParteDeRecepcion> obtenerUno(Integer id) {
        ArrayList<ADetalleParteDeRecepcion> arrayDetalleParteDeRecepcion = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  detalleparterecepcion WHERE id_detalleparterecepcion=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                  traer = new ADetalleParteDeRecepcion();
                traer.setCantidad(resultSet.getInt("cantidad"));
                traer.setCantidadRechazada(resultSet.getInt("cantidadrechazada"));
                traer.setCantidadAceptada(resultSet.getInt("cantidadaceptada"));
                traer.setCantidadRecibida(resultSet.getInt("cantidadrecibida"));
                traer.setDetalleOrdenDeCompra(resultSet.getInt("id_detalleordendecompra"));
                traer.setIdDetalleParteRecepcion(resultSet.getInt("id_detalleparterecepcion"));
                traer.setiDPartederecepcion(resultSet.getInt("id_partederecepcion"));
                traer.setIdProducto(resultSet.getInt("id_producto"));
                        traer.setiDPartederecepcion(resultSet.getInt("idusuarioverificacion"));
                arrayDetalleParteDeRecepcion.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayDetalleParteDeRecepcion;
    }

    @Override
    public int insertar(ADetalleParteDeRecepcion a) {
        int idDetalleParteDeRecepcion = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO detalleparterecepcion(id_partederecepcion, "
                    + "cantidad, id_producto, cantidadrecibida, cantidadaceptada, cantidadrechazada, id_detalleordendecompra,"
                    + "idusuarioverificacion) "
                    + "VALUES (?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getiDPartederecepcion());
            miStatement.setInt(2, a.getCantidad());
            miStatement.setInt(3, a.getIdProducto());
            miStatement.setInt(4, (int) a.getCantidadRecibida());
            miStatement.setInt(5, (int) a.getCantidadAceptada());
            miStatement.setInt(6, (int) a.getCantidadRechazada());
            miStatement.setInt(7, a.getDetalleOrdenDeCompra());
            miStatement.setInt(8, a.getIdUsuarioVerificacion());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {
                idDetalleParteDeRecepcion = resultSet.getInt(1);
                System.out.println(idDetalleParteDeRecepcion);
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
        return idDetalleParteDeRecepcion;
    }

    @Override
    public void modificar(ADetalleParteDeRecepcion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ADetalleParteDeRecepcion> lista) {
        lista.forEach((ADetalleParteDeRecepcion a) -> {
            try {
                miStatement = conexion.prepareStatement("UPDATE detalleparterecepcion SET "
                        + "id_partederecepcion= ? ,"
                        + "cantidad= ?,id_producto= ?,"
                        + "cantidadrecibida= ?,cantidadaceptada= ?,"
                        + "cantidadrechazada= ?,id_detalleordendecompra= ?,idusuarioverificacion= ? WHERE id_detalleparterecepcion= ?");
                miStatement.setInt(1, a.getiDPartederecepcion());
                miStatement.setInt(2, a.getCantidad());
                miStatement.setInt(3, a.getIdProducto());
                miStatement.setDouble(4, a.getCantidadRecibida());
                miStatement.setDouble(5, a.getCantidadAceptada());
                miStatement.setDouble(6, a.getCantidadRechazada());
                miStatement.setInt(7, a.getDetalleOrdenDeCompra());
                miStatement.setInt(8, a.getIdUsuarioVerificacion());
                miStatement.setInt(9, a.getIdDetalleParteRecepcion());
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
    public ArrayList<ADetalleParteDeRecepcion> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetalleParteDeRecepcion> obtenerPorIdCabecera(int idCabecera) {
        ArrayList<ADetalleParteDeRecepcion> arrayDetalleParteDeRecepcion = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  detalleparterecepcion Where id_partederecepcion= ? ");
            miStatement.setInt(1, idCabecera);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ADetalleParteDeRecepcion();
                traer.setCantidad(resultSet.getInt("cantidad"));
              traer.setCantidadRechazada(resultSet.getInt("cantidadrechazada"));
                traer.setCantidadAceptada(resultSet.getInt("cantidadaceptada"));
                traer.setCantidadRecibida(resultSet.getInt("cantidadrecibida"));
                traer.setDetalleOrdenDeCompra(resultSet.getInt("id_detalleordendecompra"));
                traer.setIdDetalleParteRecepcion(resultSet.getInt("id_detalleparterecepcion"));
                traer.setiDPartederecepcion(resultSet.getInt("id_partederecepcion"));
                traer.setIdProducto(resultSet.getInt("id_producto"));
                arrayDetalleParteDeRecepcion.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleParteDeRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayDetalleParteDeRecepcion;
    }

}
