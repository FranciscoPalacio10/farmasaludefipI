/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.conectarBD.ConexionB;
import interfaces.IPermisosXRol;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import farmasalud.atributosclases.APermisosXRol;



/**
 *
 * @author Francisco Palacio
 */
public class OPermisosXRol extends ConexionB implements IPermisosXRol {
    private Connection conexion;
    private APermisosXRol permisos;
    public OPermisosXRol(Connection conexion) {
        this.conexion=conexion;
    
    }

    @Override
    public ArrayList<APermisosXRol> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<APermisosXRol> obtenerUno(Integer id_rol) {
     ArrayList<APermisosXRol>  arrayListPermisosXRol=  new ArrayList<>(); 
        try {
            miStatement=conexion.prepareStatement("SELECT * "
                    + "FROM permisosxroles WHERE id_rol=?");
            miStatement.setInt(1, id_rol);
            resultSet = miStatement.executeQuery();
            while(resultSet.next()){
                permisos= new APermisosXRol();
               permisos.setIdPermisos(resultSet.getInt("id_permisos"));
               permisos.setIdRol(resultSet.getInt("id_rol"));
                arrayListPermisosXRol.add(permisos);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(OPermisosXRol.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OPermisosXRol.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OPermisosXRol.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        return arrayListPermisosXRol;
}
    @Override
    public int insertar(APermisosXRol a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(APermisosXRol a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<APermisosXRol> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public void actualizar(ArrayList<APermisosXRol> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    }

    