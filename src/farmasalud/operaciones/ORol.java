/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.conectarBD.ConexionB;
import interfaces.IRol;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import farmasalud.atributosclases.APermisosXRol;
import farmasalud.atributosclases.ARol;


/**
 *
 * @author Francisco Palacio
 */
public class ORol extends ConexionB implements IRol {
       private Connection conexion;
       private ARol roles;

    public ORol(Connection conexion) {
        this.conexion=conexion;
    }
       
       
    @Override
    public ArrayList<ARol> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ARol> obtenerUno(Integer id_rol) {
        ArrayList<ARol> arrayListRol = new ArrayList<>(); 
        try {
            miStatement=conexion.prepareStatement("SELECT * "
                    + "FROM roles WHERE id_rol=?");
            miStatement.setInt(1, id_rol);
            resultSet = miStatement.executeQuery();
            while(resultSet.next()){
               roles= new ARol();
               roles.setRol(id_rol);
               roles.setDescripcionRol(resultSet.getString("descripcion"));
                arrayListRol.add(roles);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger( IRol.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger( IRol.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger( IRol.class.getName()).log(Level.SEVERE, null, ex);
            }     
    }
        return arrayListRol;
}
    @Override
    public int insertar(ARol a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ARol a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ARol> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void actualizar(ArrayList<ARol> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
