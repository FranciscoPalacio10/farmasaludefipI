/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;


import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ANombreComercial;
import farmasalud.conectarBD.ConexionB;
import interfaces.INombreComercial;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ONombreComercial extends ConexionB implements INombreComercial{
private  ANombreComercial traer;
 private final Connection conexion;
    public ONombreComercial (Connection conexion) {
        this.conexion=conexion;
    }

    @Override
    public ArrayList<ANombreComercial> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ANombreComercial> obtenerUno(Integer id) {
        ArrayList<ANombreComercial> arrayListNombreComercial = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  nombrecomercialproductos WHERE id_nombrecomercial=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ANombreComercial();
                traer.setIdNombreComercial(resultSet.getInt("id_nombrecomercial"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListNombreComercial.add(traer);
   
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListNombreComercial;
    }

    @Override
    public int insertar(ANombreComercial a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ANombreComercial a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public ArrayList<ANombreComercial> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ANombreComercial> obtenerNombreComercialProductosFarmace(int idProducto) {
        ArrayList<ANombreComercial> arrayListNombreComercial = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT nc.descripcion,nc.id_nombrecomercial FROM nombrecomercialproductos nc, vademecums vd, productos pr "
                    + "WHERE pr.id_producto=? "
                    + "and pr.id_vademecum=vd.id_vademecum "
                    + "and nc.id_nombrecomercial=vd.nombrecomercial");
            miStatement.setInt(1, idProducto);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ANombreComercial();
                traer.setIdNombreComercial(resultSet.getInt("id_nombrecomercial"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListNombreComercial.add(traer);
   
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListNombreComercial;
    }

    @Override
    public ArrayList<ANombreComercial> obtenerNombreComercialProductosNoFarmace(int idProducto) {
      ArrayList<ANombreComercial> arrayListNombreComercial = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT nc.descripcion,nc.id_nombrecomercial FROM nombrecomercialproductos nc, nofamaceutico nf, productos pr "
                    + "WHERE pr.id_producto=? "
                    + "and pr.id_nofamaceutico=nf.id_nofamaceutico "
                    + "and nc.id_nombrecomercial=nf.nombrecomercial");
            miStatement.setInt(1, idProducto);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ANombreComercial();
                traer.setIdNombreComercial(resultSet.getInt("id_nombrecomercial"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListNombreComercial.add(traer);
   
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ONombreComercial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListNombreComercial;
    }

    @Override
    public void actualizar(ArrayList<ANombreComercial> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
  
    
   
    
}
