/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraOferta;
import farmasalud.atributosclases.ADetalleOferta;
import farmasalud.conectarBD.ConexionB;
import interfaces.IDetalleOferta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ODetalleOferta extends ConexionB implements IDetalleOferta{
 private ADetalleOferta obtenerDetalle;
    private Connection conexion;

    public ODetalleOferta(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public ArrayList<ADetalleOferta> obtenerTodos() {
      ArrayList<ADetalleOferta> arrayListDetalleOferta = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleoferta");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleOferta();
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetalleCotizacion(resultSet.getInt("id_detallecotizacion"));
                obtenerDetalle.setIdDetalleOferta(resultSet.getInt("id_detalleoferta"));
                obtenerDetalle.setIdOferta(resultSet.getInt("id_oferta"));
                obtenerDetalle.setIva(resultSet.getDouble("iva"));
                obtenerDetalle.setPrecio(resultSet.getInt("precio"));
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                arrayListDetalleOferta.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleOferta;
    }

    @Override
    public ArrayList<ADetalleOferta> obtenerUno(Integer id) {
          ArrayList<ADetalleOferta> arrayListDetalleOferta = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM detalleoferta WHERE 	id_detalleoferta=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ADetalleOferta();
                obtenerDetalle.setCantidad(resultSet.getInt("cantidad"));
                obtenerDetalle.setIdDetalleCotizacion(resultSet.getInt("id_detallecotizacion"));
                obtenerDetalle.setIdDetalleOferta(resultSet.getInt("id_detalleoferta"));
                obtenerDetalle.setIdOferta(resultSet.getInt("id_oferta"));
                obtenerDetalle.setIva(resultSet.getDouble("iva"));
                obtenerDetalle.setPrecio(resultSet.getInt("precio"));
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                arrayListDetalleOferta.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ODetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ODetalleOferta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListDetalleOferta;
    }

    @Override
    public int insertar(ADetalleOferta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ADetalleOferta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ADetalleOferta> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ADetalleOferta> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
