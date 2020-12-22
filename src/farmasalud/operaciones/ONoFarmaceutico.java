/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraCotizacion;
import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.conectarBD.ConexionB;
import interfaces.INoFarmaceutico;
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
public class ONoFarmaceutico extends ConexionB implements INoFarmaceutico {
private ANoFarmaceutico traer;
    private final Connection conexion;
    public ONoFarmaceutico (Connection conexion) {
        this.conexion=conexion;
    }
    @Override
    public ArrayList<ANoFarmaceutico> obtenerTodos() {
       ArrayList<ANoFarmaceutico> arrayListNoFarmaceutico = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM nofamaceutico");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ANoFarmaceutico();
                traer.setDescripcion(resultSet.getString("descripcion"));
                traer.setIdCategoria(resultSet.getInt("id_categoria"));
                traer.setIdGenero(resultSet.getInt("id_genero"));
                traer.setIdMarca(resultSet.getInt("id_marca"));
                traer.setIdMedida(resultSet.getString("id_medida"));
                traer.setIdNoFarmceutico(resultSet.getInt("id_nofamaceutico"));
                traer.setNombreComercial(resultSet.getInt("nombrecomercial"));
                traer.setTamano(resultSet.getInt("tamano"));
                arrayListNoFarmaceutico.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONoFarmaceutico.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ONoFarmaceutico.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ONoFarmaceutico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListNoFarmaceutico ;
    }

    @Override
    public ArrayList<ANoFarmaceutico> obtenerUno(Integer id) {
        ArrayList<ANoFarmaceutico> arrayListNoFarmaceutico = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM nofamaceutico WHERE id_nofamaceutico=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ANoFarmaceutico();
                traer.setDescripcion(resultSet.getString("descripcion"));
                traer.setIdCategoria(resultSet.getInt("id_categoria"));
                traer.setIdGenero(resultSet.getInt("id_genero"));
                traer.setIdMarca(resultSet.getInt("id_marca"));
                traer.setIdMedida(resultSet.getString("id_medida"));
                traer.setIdNoFarmceutico(resultSet.getInt("id_nofamaceutico"));
                traer.setNombreComercial(resultSet.getInt("nombrecomercial"));
                traer.setTamano(resultSet.getInt("tamano"));
                arrayListNoFarmaceutico.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONoFarmaceutico.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(ONoFarmaceutico.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(ONoFarmaceutico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListNoFarmaceutico ;
    }

    @Override
    public int insertar(ANoFarmaceutico a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ANoFarmaceutico a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public ArrayList<ANoFarmaceutico> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ANoFarmaceutico> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
