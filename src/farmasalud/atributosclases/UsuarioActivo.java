/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

import farmasalud.operaciones.OManager;
import farmasalud.conectarBD.ConexionB;
import farmasalud.conectarBD.ConexionBD;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class UsuarioActivo {

    private static UsuarioActivo obtenerInstancia;
    private OManager manager;
    private String sucursal, rol;
    private String usuario;
    private int idUsuario;
    private ArrayList<AUsuario> arrayListUsuario = new ArrayList<>();
    private ArrayList<APermisosXRol> arrayListPermisos = new ArrayList<>();

    private UsuarioActivo() {

    }

    public static UsuarioActivo getInstancia() {
        if (obtenerInstancia == null) {
            obtenerInstancia = new UsuarioActivo();
        }
        return obtenerInstancia;
    }

    public ArrayList<APermisosXRol> getArrayListPermisos() {
        return arrayListPermisos;
    }

    public void setArrayListPermisos(ArrayList<APermisosXRol> arrayListPermisos) {
        this.arrayListPermisos = arrayListPermisos;
    }

    public ArrayList<AUsuario> getArrayListUsuario() {
        return arrayListUsuario;
    }

    public void setArrayListUsuario(ArrayList<AUsuario> arrayListUsuario) {
        this.arrayListUsuario = arrayListUsuario;
    }

    public String getSucursal() {
        return sucursal;
    }

    public String getRol() {
        return rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void obtenerDatos() {
        manager = new OManager();
        arrayListUsuario.forEach((AUsuario obtener) -> {
            this.sucursal = obtener.getIdSucursal();
            this.usuario = obtener.getUsuario();
            this.idUsuario = obtener.getIdUsuario();
            manager.getRol().obtenerUno(obtener.getId_rol()).forEach((ARol obtenerRol) -> {
                rol = obtenerRol.getDescripcionRol();
                this.setArrayListPermisos(manager.getPermisosXRol().obtenerUno(obtener.getId_rol()));
            });
        });
    }

}

/*
    private String consultarTablaUsuario(String valorAobtener,String variable) {
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM usuario WHERE usuario=?");
            miStatement.setString(1, usuario);
            resultSet = miStatement.executeQuery();
            while(resultSet.next()){
                variable = resultSet.getString(valorAobtener);
                System.out.println(variable);
            }
          cerrarStament();
          cerrarResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioActivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return variable;
    }
   
      private ArrayList<String> retornarPermisosXRol(String rol){
            ArrayList<String> arrL1 = new ArrayList<>(); 
            
           try {
               miStatement=conexion.prepareStatement("SELECT id_permisos FROM permisosxroles WHERE id_rol='" +rol+ "'");
                resultSet=miStatement.executeQuery();
                   while(resultSet.next()){
                       arrL1.add(resultSet.getString("id_permisos"));
                                   }
          cerrarStament();
          cerrarResultSet();
       
           } catch (SQLException ex) {
               Logger.getLogger(UsuarioActivo.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        return arrL1;     
   }
       
   public ArrayList<String> retornarPermisosUsario(String rol) {
          int tamano=retornarPermisosXRol(rol).size();
          ArrayList<String> arrL2 = new ArrayList<>(); 
          for(int i=0;i<tamano;i++){
          String id_permiso=retornarPermisosXRol(rol).get(i);
              try {
                  miStatement=conexion.prepareStatement("SELECT * FROM permisos WHERE id_permisos='" +id_permiso+ "'");
                   resultSet=miStatement.executeQuery();
                     while(resultSet.next()){
                       arrL2.add(resultSet.getString("operacion"));
                                   }
          cerrarStament();
          cerrarResultSet();
              } catch (SQLException ex) {
                  Logger.getLogger(UsuarioActivo.class.getName()).log(Level.SEVERE, null, ex);
              }
             
          }
          
       return arrL2;     
 */
