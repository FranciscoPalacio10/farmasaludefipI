/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.atributosclases.ASucursales;
import farmasalud.conectarBD.ConexionB;
import interfaces.ISucursales;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
class OSucursales extends ConexionB implements ISucursales {
private ASucursales traer;
    private final Connection conexion;
    public OSucursales (Connection conexion) {
        this.conexion=conexion;
    }
    @Override
    public ArrayList<ASucursales> obtenerTodos() {
        ArrayList<ASucursales> arrayListSucursales = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM sucursales");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ASucursales();
                traer.setIdSucursal(resultSet.getString("id_sucursal"));
                traer.setDomicilio(resultSet.getString("domicilio"));
                arrayListSucursales.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OSucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OSucursales.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OSucursales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListSucursales;
    }

    @Override
    public ArrayList<ASucursales> obtenerUno(String id) {
        ArrayList<ASucursales> arrayListSucursales = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM sucursales WHERE id_sucursal=?");
            miStatement.setString(1,id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ASucursales();
                traer.setIdSucursal(resultSet.getString("id_sucursal"));
                traer.setDomicilio(resultSet.getString("domicilio"));
                arrayListSucursales.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OSucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OSucursales.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OSucursales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListSucursales;
    }

    @Override
    public int insertar(ASucursales a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ASucursales a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public ArrayList<ASucursales> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ASucursales> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
