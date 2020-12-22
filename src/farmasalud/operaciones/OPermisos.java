/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.atributosclases.UsuarioActivo;
import farmasalud.conectarBD.ConexionB;
import java.util.ArrayList;
import farmasalud.atributosclases.APermisos;

import farmasalud.conectarBD.ConexionBD;
import interfaces.IPermisos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import farmasalud.atributosclases.APermisosXRol;



public class OPermisos extends ConexionB implements IPermisos {
    private APermisos permisos;
    private Connection conexion;
    public OPermisos(Connection conexion) {
        this.conexion=conexion;

    }
    
    @Override
    public ArrayList<APermisos> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<APermisos> obtenerUno(Integer id_permisos) {
        ArrayList<APermisos> arrayListPermisos = new ArrayList<>();
             try {
            miStatement=conexion.prepareStatement("SELECT * "
                    + "FROM permisos WHERE id_permisos=?");
            miStatement.setInt(1, id_permisos);
            resultSet = miStatement.executeQuery();
            while(resultSet.next()){
                permisos= new APermisos();
               permisos.setId_Permisos(id_permisos);
               permisos.setDescripcion(resultSet.getString("descripcion"));
               permisos.setOperaciones(resultSet.getString("operaciones"));
                arrayListPermisos.add(permisos);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioActivo.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
             return arrayListPermisos;
}
    @Override
    public int insertar(APermisos a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(APermisos a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<APermisos> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void actualizar(ArrayList<APermisos> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
