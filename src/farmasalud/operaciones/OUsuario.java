/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.atributosclases.AUsuario;
import farmasalud.conectarBD.ConexionB;
import interfaces.IUsuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Francisco Palacio
 */
public class OUsuario extends ConexionB implements IUsuario{
    private AUsuario usuarioB;
    private final Connection conexion;
    public OUsuario(Connection conexion) {
        this.conexion=conexion;
        
    }

    
    
    @Override
    public ArrayList<String> retornarPermisosXRol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AUsuario> obtenerTodos() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //Binary
    @Override
    public ArrayList<AUsuario> obtenerUno(String usuario) {
        ArrayList<AUsuario> obtener = new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM usuario WHERE  usuario=?");
            miStatement.setString(1, usuario);
            resultSet = miStatement.executeQuery();
            while(resultSet.next()){
                usuarioB=new AUsuario();
                usuarioB.setIdUsuario(resultSet.getInt("usuario.id_usuario"));
                usuarioB.setNombre(resultSet.getString("usuario.nombre"));
                usuarioB.setApellido(resultSet.getString("usuario.apellido"));
                usuarioB.setUsuario(resultSet.getString("usuario.usuario"));
                usuarioB.setContrasena(resultSet.getString("usuario.contrasena"));
                usuarioB.setIdSucursal(resultSet.getString("usuario.id_sucursal"));
                usuarioB.setId_rol(resultSet.getInt("usuario.id_rol"));
                obtener.add(usuarioB);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(OUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        return obtener;
    }

    @Override
    public int insertar(AUsuario a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(AUsuario a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AUsuario> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public ArrayList<AUsuario> obtenerUsuarioXId(int id) {
      ArrayList<AUsuario> obtener = new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM usuario WHERE id_usuario=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while(resultSet.next()){
                usuarioB=new AUsuario();
                usuarioB.setIdUsuario(resultSet.getInt("usuario.id_usuario"));
                usuarioB.setNombre(resultSet.getString("usuario.nombre"));
                usuarioB.setApellido(resultSet.getString("usuario.apellido"));
                usuarioB.setUsuario(resultSet.getString("usuario.usuario"));
                usuarioB.setContrasena(resultSet.getString("usuario.contrasena"));
                usuarioB.setIdSucursal(resultSet.getString("usuario.id_sucursal"));
                usuarioB.setId_rol(resultSet.getInt("usuario.id_rol"));
                obtener.add(usuarioB);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(OUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
        return obtener;
    }

    @Override
    public void actualizar(ArrayList<AUsuario> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
