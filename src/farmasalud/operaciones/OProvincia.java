/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.APais;
import farmasalud.atributosclases.AProvincia;
import farmasalud.conectarBD.ConexionB;
import interfaces.IProvincia;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OProvincia extends ConexionB implements IProvincia {

    private AProvincia traer;
    private final Connection conexion;

    public OProvincia(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<AProvincia> obtenerTodos() {
           ArrayList<AProvincia> arrayListProvincia = new ArrayList<>();
        try {
             miStatement = conexion.prepareStatement("SELECT * FROM  provincia");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AProvincia();
                traer.setIdProvincia(resultSet.getString("id_estado"));
                traer.setIdPais(resultSet.getString("id_pais"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListProvincia.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OPais.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OPais.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OPais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListProvincia;
    }

    @Override
    public ArrayList<AProvincia> obtenerUno(String id) {
        ArrayList<AProvincia> arrayListProvincia = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  provincia WHERE id_estado=?");
            miStatement.setString(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AProvincia();
                traer.setIdProvincia(resultSet.getString("id_estado"));
                traer.setIdPais(resultSet.getString("id_pais"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListProvincia.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OProvincia.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OProvincia.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OProvincia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListProvincia;
    }

    @Override
    public int insertar(AProvincia a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(AProvincia a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<AProvincia> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AProvincia> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AProvincia> obtenerPaisXProvincia(String idPais) {
            ArrayList<AProvincia> arrayListProvincia = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  provincia WHERE id_pais=?");
            miStatement.setString(1, idPais);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AProvincia();
                traer.setIdProvincia(resultSet.getString("id_estado"));
                traer.setIdPais(resultSet.getString("id_pais"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListProvincia.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OPais.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OPais.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OPais.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListProvincia;
    }

    @Override
    public String obtenerId(String descripcion) {
         String id = null;
         try {
           miStatement = conexion.prepareStatement("SELECT * FROM  provincia WHERE descripcion=?");
            miStatement.setString(1, descripcion);
            resultSet=miStatement.executeQuery();
              while(resultSet.next()){

               id=resultSet.getString("id_estado");
            
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return id;
    }

  

}
