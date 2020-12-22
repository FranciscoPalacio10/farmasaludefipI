/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.AProveedor;
import farmasalud.atributosclases.ASucursales;
import farmasalud.atributosclases.ATipoProveedor;
import farmasalud.conectarBD.ConexionB;
import interfaces.ITipoProveedor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OTipoProveedor extends ConexionB implements ITipoProveedor {
private ATipoProveedor traer;
    private final Connection conexion;
    public OTipoProveedor (Connection conexion) {
        this.conexion=conexion;
    }

    @Override
    public ArrayList<ATipoProveedor> obtenerTodos() {
     ArrayList<ATipoProveedor> arrayListTipoProveedor = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM tipoproveedor");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ATipoProveedor();
                traer.setIdTipoProveedor(resultSet.getInt("id_tipoproveedor"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListTipoProveedor.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListTipoProveedor;
    }

    @Override
    public ArrayList<ATipoProveedor> obtenerUno(Integer id) {
       ArrayList<ATipoProveedor> arrayListTipoProveedor = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM tipoproveedor WHERE 	id_tipoproveedor=?");
            miStatement.setInt(1,id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ATipoProveedor();
                traer.setIdTipoProveedor(resultSet.getInt("id_tipoproveedor"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListTipoProveedor.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListTipoProveedor;
    }

    @Override
    public int insertar(ATipoProveedor a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ATipoProveedor a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public ArrayList<ATipoProveedor> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ATipoProveedor> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obtenerID(String descripcion) {
        int id=0;
         try {
            miStatement = conexion.prepareStatement("SELECT * FROM tipoproveedor WHERE descripcion=?");
            miStatement.setString(1, descripcion);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ATipoProveedor();
               id=resultSet.getInt("id_tipoproveedor");
             
            }

        } catch (SQLException ex) {
            Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OTipoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return id;
    }
   
    
}
