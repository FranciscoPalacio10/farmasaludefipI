/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraOrdenDeCompra;
import farmasalud.atributosclases.ADetalleOrdenDeCompra;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetalleOrdenDeCompra;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ODetalleOrdenDeCompra extends ConexionB implements IDetalleOrdenDeCompra {

    private ADetalleOrdenDeCompra obtenerDetalle;
    private Connection conexion;

    public ODetalleOrdenDeCompra(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<ADetalleOrdenDeCompra> obtenerTodos() {
        ArrayList<ADetalleOrdenDeCompra> arrayListDetalleOrdenDeCompra = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleordendecompra");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleOrdenDeCompra();
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetalleOC(resultSet.getInt("id_detalleoc"));
                obtenerDetalle.setIdDetalleOferta(resultSet.getInt("id_detalleoferta"));
                obtenerDetalle.setId_producto(resultSet.getInt("id_producto"));
                obtenerDetalle.setNumeroOC(resultSet.getInt("id_ordendecompra"));
                obtenerDetalle.setPrecioUnitario(resultSet.getInt("preciounitario"));
                arrayListDetalleOrdenDeCompra.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleOrdenDeCompra;
    }

    @Override
    public ArrayList<ADetalleOrdenDeCompra> obtenerUno(Integer id) {
        ArrayList<ADetalleOrdenDeCompra> arrayListDetalleOrdenDeCompra = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleordendecompra WHERE 	id_detalleoc=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleOrdenDeCompra();
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetalleOC(resultSet.getInt("id_detalleoc"));
                obtenerDetalle.setIdDetalleOferta(resultSet.getInt("id_detalleoferta"));
                obtenerDetalle.setId_producto(resultSet.getInt("id_producto"));
                obtenerDetalle.setNumeroOC(resultSet.getInt("id_ordendecompra"));
                obtenerDetalle.setPrecioUnitario(resultSet.getInt("preciounitario"));
                arrayListDetalleOrdenDeCompra.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleOrdenDeCompra;
    }

    @Override
    public int insertar(ADetalleOrdenDeCompra a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ADetalleOrdenDeCompra a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ADetalleOrdenDeCompra> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetalleOrdenDeCompra> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetalleOrdenDeCompra> obtenerDetaleXIdCabcera(int idOrdenDeCompra) {
       ArrayList<ADetalleOrdenDeCompra> arrayListDetalleOrdenDeCompra = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleordendecompra WHERE id_ordendecompra=?");
            miStatement.setInt(1, idOrdenDeCompra);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleOrdenDeCompra();

                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetalleOC(resultSet.getInt("id_detalleoc"));
                obtenerDetalle.setIdDetalleOferta(resultSet.getInt("id_detalleoferta"));
                obtenerDetalle.setId_producto(resultSet.getInt("id_producto"));
                obtenerDetalle.setNumeroOC(resultSet.getInt("id_ordendecompra"));
                obtenerDetalle.setPrecioUnitario(resultSet.getInt("preciounitario"));
                arrayListDetalleOrdenDeCompra.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleOrdenDeCompra;
    }

}
