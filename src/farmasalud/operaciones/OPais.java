/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ANombreGenerico;
import farmasalud.atributosclases.APais;
import farmasalud.conectarBD.ConexionB;
import interfaces.IPais;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OPais extends ConexionB implements IPais {
private  APais  traer;
 private final Connection conexion;
    public  OPais (Connection conexion) {
        this.conexion=conexion;
    }
   
    @Override
    public ArrayList<APais> obtenerTodos() {
      ArrayList<APais> arrayListPais = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  pais");
            
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new APais();
                traer.setIdPais(resultSet.getString("id_pais"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListPais.add(traer);
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
        return arrayListPais;
    }

    @Override
    public ArrayList<APais> obtenerUno(String id) {
      ArrayList<APais> arrayListPais = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  pais WHERE id_pais=?");
            miStatement.setString(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new APais();
                traer.setIdPais(resultSet.getString("id_pais"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListPais.add(traer);
   
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
        return arrayListPais;
    }

    @Override
    public int insertar(APais a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(APais a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<APais> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<APais> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerCodigoPais(String descripscion) {
        String idPais = null;
         try {
            miStatement = conexion.prepareStatement("SELECT * FROM  pais WHERE descripcion=?");
            miStatement.setString(1, descripscion);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new APais();
                 idPais=resultSet.getString("id_pais");
            
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
    return idPais;
    }
    
}
