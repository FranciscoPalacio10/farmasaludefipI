/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ACabeceraPedidos;
import farmasalud.atributosclases.ACondicionAfip;
import farmasalud.conectarBD.ConexionB;
import interfaces.ICondicionAfip;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OCondicionAfip extends ConexionB implements ICondicionAfip {
   private final Connection conexion;
    public OCondicionAfip (Connection conexion) {   
        this.conexion=conexion; 
    }
 
    
    @Override
    public ArrayList<ACondicionAfip> obtenerTodos() {
       ArrayList<ACondicionAfip> arrayListCondcionAfip= new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM condicionafip");
            resultSet=miStatement.executeQuery();
              while(resultSet.next()){
            ACondicionAfip condiciones=new ACondicionAfip();
                condiciones.setIdCondicionAfip(resultSet.getInt("codigo"));
                condiciones.setDescripcion(resultSet.getString("descripcion"));
                        
                arrayListCondcionAfip.add(condiciones);
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCondcionAfip;
    }

    @Override
    public ArrayList<ACondicionAfip> obtenerUno(Integer id) {
       ArrayList<ACondicionAfip> arrayListCondcionAfip= new ArrayList<>();
        try {
            miStatement=conexion.prepareStatement("SELECT * FROM condicionafip WHERE codigo=? ");
            miStatement.setInt(1, id);
            resultSet=miStatement.executeQuery();
              while(resultSet.next()){
            ACondicionAfip condiciones=new ACondicionAfip();
                condiciones.setIdCondicionAfip(resultSet.getInt("codigo"));
                condiciones.setDescripcion(resultSet.getString("descripcion"));        
                arrayListCondcionAfip.add(condiciones);
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListCondcionAfip;
    }

    @Override
    public int insertar(ACondicionAfip a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(ACondicionAfip a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<ACondicionAfip> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ACondicionAfip> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obtenerId(String condcion) {
        int id = 0;
         try {
            miStatement=conexion.prepareStatement("SELECT * FROM condicionafip WHERE descripcion=? ");
            miStatement.setString(1, condcion);
            resultSet=miStatement.executeQuery();
              while(resultSet.next()){

               id=resultSet.getInt("codigo");
            
            }
       
        } catch (SQLException ex) {
            Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCondicionAfip.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         return id;
    }
    
    
    

    
}
