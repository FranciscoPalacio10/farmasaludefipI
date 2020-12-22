/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;;

import farmasalud.atributosclases.ANombreComercial;
import farmasalud.atributosclases.ANombreGenerico;
import farmasalud.conectarBD.ConexionB;
import interfaces.INombreGenerico;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class ONombreGenerico extends ConexionB implements INombreGenerico {
private  ANombreGenerico  traer;
 private final Connection conexion;
    public ONombreGenerico (Connection conexion) {
        this.conexion=conexion;
    }
    @Override
    public ArrayList<ANombreGenerico> obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ANombreGenerico> obtenerUno(Integer id) {
            ArrayList<ANombreGenerico> arrayListNombreGenerico = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM  nombregenerico WHERE id_nombregenerico=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new ANombreGenerico();
                traer.setIdNombreGenerico(resultSet.getInt("id_nombregenerico"));
                traer.setDescripcion(resultSet.getString("descripcion"));
                arrayListNombreGenerico.add(traer);
   
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
        return arrayListNombreGenerico;
    }

    @Override
    public int insertar(ANombreGenerico a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ANombreGenerico a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public ArrayList<ANombreGenerico> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ANombreGenerico> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
