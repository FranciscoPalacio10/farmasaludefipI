/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACondicionReceta;
import farmasalud.atributosclases.ACotizacionXProveedor;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICotizacionXProveedor;
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
public class OCotizacionXProveedor extends ConexionB implements ICotizacionXProveedor {
    private ACotizacionXProveedor traer;  
    private final Connection conexion;
    public OCotizacionXProveedor (Connection conexion) {   
        this.conexion=conexion; 
    }
    @Override
    public ArrayList<ACotizacionXProveedor> obtenerTodos() {
          ArrayList<ACotizacionXProveedor> arrayListCotizacionXProveedor= new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  cotizacionxproveedor");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new  ACotizacionXProveedor();
               traer.setIdCotiazacion(resultSet.getInt("id_cotizacion"));
               traer.setIdProveedor(resultSet.getInt("id_proveedor"));   
                arrayListCotizacionXProveedor.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCotizacionXProveedor ;
    }

    @Override
    public ArrayList<ACotizacionXProveedor> obtenerUno(Integer id) {
      ArrayList<ACotizacionXProveedor> arrayListCotizacionXProveedor= new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  cotizacionxproveedor WHERE id_cotizacion=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new  ACotizacionXProveedor();
               traer.setIdCotiazacion(resultSet.getInt("id_cotizacion"));
               traer.setIdProveedor(resultSet.getInt("id_proveedor"));   
                arrayListCotizacionXProveedor.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCotizacionXProveedor ;
    }

    @Override
    public int insertar(ACotizacionXProveedor a) {
        int idCotizacionInsertada = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO cotizacionxproveedor(id_cotizacion,id_proveedor) "
                    + "VALUES (?,?)");
            miStatement.setInt(1, a.getIdCotiazacion());
            miStatement.setInt(2,a.getIdProveedor());
            miStatement.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(OCotizacionXProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
    public void modificar(ACotizacionXProveedor a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ACotizacionXProveedor> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ACotizacionXProveedor> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
