/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ACondicionReceta;
import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICondicionReceta;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OCondicionReceta extends ConexionB implements ICondicionReceta {
    private ACondicionReceta traer;  
    private final Connection conexion;
    public OCondicionReceta(Connection conexion) {   
        this.conexion=conexion; 
    }
    @Override
    public ArrayList<ACondicionReceta> obtenerTodos() {
     ArrayList<ACondicionReceta> arrayListCondicionReceta = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM condicionreceta");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new  ACondicionReceta();
               traer.setIdCondcionReceta(resultSet.getInt("id_condcionreceta"));
               traer.setDescripcion(resultSet.getString("descripcion"));   
                arrayListCondicionReceta.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCondicionReceta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionReceta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionReceta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCondicionReceta ;
    }

    @Override
    public ArrayList<ACondicionReceta> obtenerUno(Integer id) {
          ArrayList<ACondicionReceta> arrayListCondicionReceta = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM condicionreceta WHERE id_condcionreceta=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new  ACondicionReceta();
               traer.setIdCondcionReceta(resultSet.getInt("id_condcionreceta"));
               traer.setDescripcion(resultSet.getString("descripcion"));
               
                arrayListCondicionReceta.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OCondicionReceta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionReceta.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionReceta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCondicionReceta ;
    }

    @Override
    public int insertar(ACondicionReceta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ACondicionReceta a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ArrayList<ACondicionReceta> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ACondicionReceta> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
