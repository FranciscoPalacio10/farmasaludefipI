/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraEnvioInterno;
import farmasalud.atributosclases.ACabeceraOrdenDeCompra;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICabeceraOrdenDeCompra;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OCabeceraOrdenDeCompra extends ConexionB implements ICabeceraOrdenDeCompra{
 private ACabeceraOrdenDeCompra obtenerDetalle;
    private Connection conexion;

    public OCabeceraOrdenDeCompra (Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public ArrayList<ACabeceraOrdenDeCompra> obtenerTodos() {
            ArrayList<ACabeceraOrdenDeCompra> arrayListCabeceraOrdenDeCompra = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM ordendecompra ");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ACabeceraOrdenDeCompra();
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                obtenerDetalle.setCondicionesPago(resultSet.getInt("condicionespago"));
                obtenerDetalle.setFechaInicio(resultSet.getDate("fechaInicio"));
                obtenerDetalle.setFechafinalizacion(resultSet.getDate("fechafinalizacion"));
                obtenerDetalle.setIdOferta(resultSet.getInt("id_oferta"));
                obtenerDetalle.setIdOrdenDeCompra(resultSet.getInt("id_ordendecompra"));
                obtenerDetalle.setIdProveedor(resultSet.getInt("id_proveedor"));
                 obtenerDetalle.setIdUsuario(resultSet.getInt("id_usuario"));
                  obtenerDetalle.setPlazoDeEntrega(resultSet.getInt("plazodeentrega"));
                arrayListCabeceraOrdenDeCompra.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCabeceraOrdenDeCompra;
    }

    @Override
    public ArrayList<ACabeceraOrdenDeCompra> obtenerUno(Integer id) {
         ArrayList<ACabeceraOrdenDeCompra> arrayListCabeceraOrdenDeCompra = new ArrayList<>();

        try {
            miStatement = conexion.prepareStatement("SELECT * FROM ordendecompra WHERE id_ordendecompra=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                obtenerDetalle = new ACabeceraOrdenDeCompra();
                obtenerDetalle.setEstado(resultSet.getString("estado"));
                obtenerDetalle.setCondicionesPago(resultSet.getInt("condicionespago"));
                obtenerDetalle.setFechaInicio(resultSet.getDate("fechaInicio"));
                obtenerDetalle.setFechafinalizacion(resultSet.getDate("fechafinalizacion"));
                obtenerDetalle.setIdOferta(resultSet.getInt("id_oferta"));
                obtenerDetalle.setIdOrdenDeCompra(resultSet.getInt("id_ordendecompra"));
                obtenerDetalle.setIdProveedor(resultSet.getInt("id_proveedor"));
                 obtenerDetalle.setIdUsuario(resultSet.getInt("	id_usuario"));
                  obtenerDetalle.setPlazoDeEntrega(resultSet.getInt("plazodeentrega"));
                arrayListCabeceraOrdenDeCompra.add(obtenerDetalle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraOrdenDeCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCabeceraOrdenDeCompra;
    }

    @Override
    public int insertar(ACabeceraOrdenDeCompra a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ACabeceraOrdenDeCompra a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ACabeceraOrdenDeCompra> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ACabeceraOrdenDeCompra> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actulizarEestado(int idCabecera,String estado) {
         try {
            miStatement = conexion.prepareStatement("UPDATE ordendecompra SET estado=? WHERE id_ordendecompra=?");
          miStatement .setString(1, estado);
          miStatement .setInt(2, idCabecera);
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
