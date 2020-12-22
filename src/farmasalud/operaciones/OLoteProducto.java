/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ALoteProducto;
import farmasalud.atributosclases.AParteDeRecepcion;
import farmasalud.conectarBD.ConexionB;
import interfaces.ILoteProducto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OLoteProducto extends ConexionB implements ILoteProducto{
 private ALoteProducto traer;
    private final Connection conexion;

    public OLoteProducto(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public ArrayList<ALoteProducto> obtenerTodos() {
      ArrayList<ALoteProducto> arrayListLoteProducto = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  loteproducto");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ALoteProducto();
                traer.setFechaEnvasado(resultSet.getDate("fechaenvasado"));
                traer.setFechaVto(resultSet.getDate("fechavto"));
                traer.setIdDetalleParteRecepcion(resultSet.getInt("id_detalleparterecepcion"));
                traer.setIdLote(resultSet.getString("id_lote"));
                traer.setIdProducto(resultSet.getInt("id_producto"));
              
                arrayListLoteProducto.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayListLoteProducto;
    }

    @Override
    public ArrayList<ALoteProducto> obtenerUno(String id) {
       ArrayList<ALoteProducto> arrayListLoteProducto = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  loteproducto WHERE id_lote=?");
            miStatement.setString(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ALoteProducto();
                traer.setFechaEnvasado(resultSet.getDate("fechaenvasado"));
                traer.setFechaVto(resultSet.getDate("fechavto"));
                traer.setIdDetalleParteRecepcion(resultSet.getInt("id_detalleparterecepcion"));
                traer.setIdLote(resultSet.getString("id_lote"));
                traer.setIdProducto(resultSet.getInt("id_producto"));
              
                arrayListLoteProducto.add(traer);

            }

        } catch (SQLException ex) {
            Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           return arrayListLoteProducto;
    }

    @Override
    public int insertar(ALoteProducto a) {
        int idALoteProducto = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO loteproducto(id_lote, id_producto, fechavto, "
                    + "fechaenvasado, id_detalleparterecepcion)"
                    + "VALUES (?,?,?,?,?)");
            miStatement.setString(1,a.getIdLote());
            miStatement.setInt(2, a.getIdProducto());
            miStatement.setDate(3, a.getFechaVto());
            miStatement.setDate(4, a.getFechaEnvasado());
            miStatement.setInt(5, a.getIdDetalleParteRecepcion());
            miStatement.executeUpdate();
          
            } catch (SQLException ex) {
            Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idALoteProducto;
    }

    @Override
    public void modificar(ALoteProducto a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ALoteProducto> lista) {
        lista.forEach((ALoteProducto a) -> {
            try {
                miStatement = conexion.prepareStatement(" UPDATE loteproducto SET "
                        + "id_producto=?,fechavto=?,"
                        + "fechaenvasado=?,id_detalleparterecepcion=?"
                        + "WHERE id_lote=?");   
            miStatement.setInt(1, a.getIdProducto());
            miStatement.setDate(2,  a.getFechaVto());
            miStatement.setDate(3,  a.getFechaEnvasado());
            miStatement.setInt(4, a.getIdDetalleParteRecepcion());
            miStatement.setString(5,a.getIdLote());
                miStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    this.cerrarStament();
                    this.cerrarResultSet();
                } catch (SQLException ex) {
                    Logger.getLogger(OLoteProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public ArrayList<ALoteProducto> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
