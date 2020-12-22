/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.conectarBD.ConexionB;
import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.pedidosSucursales.pintarCeldas;
import interfaces.ICabeceraCotizacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francisco Palacio
 */
public class OCabeceraCotizacion extends ConexionB implements ICabeceraCotizacion {

    private Connection conexion;

    public OCabeceraCotizacion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public int insertar(ACabeceraCotizacion a) {
        int idCotizacionInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO cotizacion(id_usuario,fecha,estado) "
                    + "VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setInt(1, a.getIdUsuario());
            miStatement.setDate(2,a.getFecha());
            miStatement.setString(3, a.getEstadoCotizacion());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {

                idCotizacionInsertada = resultSet.getInt(1);
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
        return idCotizacionInsertada;
    }

    @Override
    public void modificar(ACabeceraCotizacion a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ACabeceraCotizacion> obtenerUno(Integer id) {
        ArrayList<ACabeceraCotizacion> arrayListCotizacion = new ArrayList<>();
        ACabeceraCotizacion cotizacion;
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM cotizacion WHERE id_cotizacion=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                cotizacion = new ACabeceraCotizacion();
                cotizacion.setIdCotizacion(resultSet.getInt("id_cotizacion"));
                cotizacion.setEstadoCotizacion(resultSet.getString("estado"));
                cotizacion.setIdUsuario(resultSet.getInt("id_usuario"));
                cotizacion.setVigencia(resultSet.getDate("vigencia"));
                cotizacion.setFecha(resultSet.getDate("fecha"));
                cotizacion.setObservaciones(resultSet.getString("observaciones"));
                arrayListCotizacion.add(cotizacion);
                System.out.println(arrayListCotizacion );
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCotizacion;
    }

    //String estado
    @Override
    public ArrayList<ACabeceraCotizacion> ejecutarProcedimientoAlmacenado(String procedimiento) {
        ArrayList<ACabeceraCotizacion> arrayListCotizacion = new ArrayList<>();
        ACabeceraCotizacion cotizacion;
        try {
            statement = conexion.prepareCall("{call traerCotizacion(?)}");
            statement.setString(1, procedimiento);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cotizacion = new ACabeceraCotizacion();
                cotizacion.setIdCotizacion(resultSet.getInt("cotizacion.id_cotizacion"));
                cotizacion.setEstadoCotizacion(resultSet.getString("cotizacion.estado"));
                cotizacion.setIdUsuario(resultSet.getInt("cotizacion.id_usuario"));
               // cotizacion.setStringUsuario(resultSet.getString("usuario.usuario"));
                cotizacion.setVigencia(resultSet.getDate("cotizacion.vigencia"));
                cotizacion.setFecha(resultSet.getDate("cotizacion.fecha"));
                arrayListCotizacion.add(cotizacion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarProcedimiento();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCotizacion;
    }

    @Override
    public ArrayList<ACabeceraCotizacion> obtenerTodos() {
            ArrayList<ACabeceraCotizacion> arrayListCotizacion = new ArrayList<>();
        ACabeceraCotizacion cotizacion;
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM cotizacion");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                cotizacion = new ACabeceraCotizacion();
                cotizacion.setIdCotizacion(resultSet.getInt("id_cotizacion"));
                cotizacion.setEstadoCotizacion(resultSet.getString("estado"));
                cotizacion.setIdUsuario(resultSet.getInt("id_usuario"));
                cotizacion.setVigencia(resultSet.getDate("vigencia"));
                cotizacion.setFecha(resultSet.getDate("fecha"));
                cotizacion.setObservaciones(resultSet.getString("observaciones"));
                arrayListCotizacion.add(cotizacion);
                System.out.println(arrayListCotizacion );
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        }
        return arrayListCotizacion;
 }
    
  

    @Override
    public void actualizar(ArrayList<ACabeceraCotizacion> lista) {
       lista.forEach((ACabeceraCotizacion adc)->{
         try {
            miStatement = conexion.prepareStatement("UPDATE cotizacion SET estado=?,fecha=?,vigencia= ?,observaciones=?,id_usuario=? "
                    + "WHERE id_cotizacion=?");
            miStatement.setString(1,adc.getEstadoCotizacion());
            miStatement.setDate(2, adc.getFecha());
            miStatement.setDate(3, adc.getVigencia());
            miStatement.setString(4,adc.getObservaciones());       
            miStatement.setInt(5,adc.getIdUsuario());
            miStatement.setInt(6,adc.getIdCotizacion());
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
}

