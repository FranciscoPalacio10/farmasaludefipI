/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.AFormaFarmaceutica;
import farmasalud.atributosclases.ANombreComercial;
import farmasalud.conectarBD.ConexionB;
import interfaces.IFormaFarmaceutica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OFormaFarmaceutica extends ConexionB implements IFormaFarmaceutica {
private  AFormaFarmaceutica traer;
 private final Connection conexion;
    public OFormaFarmaceutica  (Connection conexion) {
        this.conexion=conexion;
    }
    @Override
    public ArrayList<AFormaFarmaceutica> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AFormaFarmaceutica> obtenerUno(Integer id) {
         ArrayList<AFormaFarmaceutica> arrayListFormaFarmaceutica = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  formafarmaceutica WHERE id_presentacion=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AFormaFarmaceutica();
                traer.setIdFormaFarmaceutica(resultSet.getInt("id_presentacion"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListFormaFarmaceutica.add(traer);
   
            }

        } catch (SQLException ex) {
            Logger.getLogger(OFormaFarmaceutica.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OFormaFarmaceutica.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OFormaFarmaceutica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListFormaFarmaceutica;
    }

    @Override
    public int insertar(AFormaFarmaceutica a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(AFormaFarmaceutica a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public ArrayList<AFormaFarmaceutica> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<AFormaFarmaceutica> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
