/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.atributosclases.AProducto;
import farmasalud.atributosclases.AVademecum;
import farmasalud.conectarBD.ConexionB;
import interfaces.IVademecum;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OVademecum extends ConexionB implements IVademecum {
  private AVademecum traer;
    private Connection conexion;
    public OVademecum (Connection conexion) {
        this.conexion=conexion;
    }

    @Override
    public ArrayList<AVademecum> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AVademecum> obtenerUno(Integer id) {
         ArrayList<AVademecum> arrayListVademecum = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  vademecums WHERE id_vademecum=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AVademecum();
                traer.setFormaFarmaceutica(resultSet.getInt("formafarmaceutica"));
                traer.setAccionTerapeutica(resultSet.getString("accionterapeutica"));
                traer.setCantidadPresentacion(resultSet.getInt("cantidadpresentacion"));
                traer.setConcentracion(resultSet.getInt("concentracion"));
                traer.setCondcionReceta(resultSet.getInt("condicionreceta"));
                traer.setIdMedida(resultSet.getString("medida"));
                traer.setNombreComercial(resultSet.getInt("nombrecomercial"));
                traer.setIdVademecum(resultSet.getInt("id_vademecum"));
                traer.setLaboratorio(resultSet.getInt("laboratorio"));
                traer.setNombreGenerico(resultSet.getInt("nombregenerico"));
                arrayListVademecum.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OVademecum.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OVademecum.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OVademecum.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListVademecum ;
    }

    @Override
    public int insertar(AVademecum a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(AVademecum a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public ArrayList<AVademecum> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<AVademecum> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
