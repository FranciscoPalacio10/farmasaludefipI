/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraOferta;
import farmasalud.atributosclases.ADetalleOrdenDeCompra;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICabeceraOferta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OCabeceraOferta extends ConexionB implements ICabeceraOferta {
     private ACabeceraOferta obtenerDetalle;
    private Connection conexion;

    public OCabeceraOferta(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public ArrayList<ACabeceraOferta> obtenerTodos() {
     ArrayList<ACabeceraOferta> arrayListCabeceraOferta = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM ofertas");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ACabeceraOferta();
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                obtenerDetalle.setFecha(resultSet.getDate("fecha"));
                obtenerDetalle.setIdCotizacion(resultSet.getInt("id_cotizacion"));
                obtenerDetalle.setIdFormadepago(resultSet.getInt("id_formadepago"));
                obtenerDetalle.setIdOferta(resultSet.getInt("id_oferta"));
                obtenerDetalle.setIdProveedor(resultSet.getInt("id_proveedor"));
                obtenerDetalle.setMantenimientoOferta(resultSet.getInt("mantenimientooferta"));
                obtenerDetalle.setPlazoDeEntrega(resultSet.getInt("plazodeentrega"));
                arrayListCabeceraOferta.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraOferta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCabeceraOferta;
    }

    @Override
    public ArrayList<ACabeceraOferta> obtenerUno(Integer id) {
        ArrayList<ACabeceraOferta> arrayListCabeceraOferta = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM ofertas WHERE id_oferta=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ACabeceraOferta();
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                obtenerDetalle.setFecha(resultSet.getDate("fecha"));
                obtenerDetalle.setIdCotizacion(resultSet.getInt("id_cotizacion"));
                obtenerDetalle.setIdFormadepago(resultSet.getInt("id_formadepago"));
                obtenerDetalle.setIdOferta(resultSet.getInt("id_oferta"));
                obtenerDetalle.setIdProveedor(resultSet.getInt("id_proveedor"));
                obtenerDetalle.setMantenimientoOferta(resultSet.getInt("mantenimientooferta"));
                obtenerDetalle.setPlazoDeEntrega(resultSet.getInt("plazodeentrega"));
                arrayListCabeceraOferta.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraOferta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCabeceraOferta;
    }

    @Override
    public int insertar(ACabeceraOferta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ACabeceraOferta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ACabeceraOferta> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ACabeceraOferta> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
