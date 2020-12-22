/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ADetalleCotizacion;
import farmasalud.atributosclases.AFormaFarmaceutica;
import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.atributosclases.ANombreComercial;
import farmasalud.atributosclases.AProducto;
import farmasalud.atributosclases.AVademecum;
import farmasalud.conectarBD.ConexionB;
import farmasalud.conectarBD.ConexionBD;
import interfaces.IProducto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
public class OProductos extends ConexionB implements IProducto {
    private AProducto traer;
    private Connection conexion;
     private final int CONSTANTEVADEMECUM = 12;
    private final int CONSTANTENOFARMACEUTICO = 593;
    public OProductos(Connection conexion) {
        this.conexion=conexion;
    }

    
    
    @Override
    public ArrayList<AProducto> obtenerTodos() {
         ArrayList<AProducto> arrayListProductos= new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM productos");
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                traer= new AProducto();
                traer.setIdProducto(resultSet.getInt("id_producto"));
                traer.setIdNoFamaceutico(resultSet.getInt("id_nofamaceutico"));
                traer.setIdVademecum(resultSet.getInt("id_vademecum"));
                traer.setPrecioUnitario(resultSet.getInt("preciounitario"));
                traer.setPuntoDePedido(resultSet.getInt("puntodepedido"));
                 arrayListProductos.add(traer);
            }     
      
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListProductos;
    }

    @Override
    public ArrayList<AProducto> obtenerUno(Integer id) {
       ArrayList<AProducto> arrayListProductos= new ArrayList<>();
        
          try {
            miStatement=conexion.prepareStatement("SELECT * FROM productos WHERE id_producto=?");
            miStatement.setInt(1, id);
            resultSet=miStatement.executeQuery();
            while(resultSet.next()){
                traer= new AProducto();
                traer.setIdProducto(resultSet.getInt("id_producto"));
                traer.setIdNoFamaceutico(resultSet.getInt("id_nofamaceutico"));
                traer.setIdVademecum(resultSet.getInt("id_vademecum"));
                traer.setPrecioUnitario(resultSet.getInt("preciounitario"));
                traer.setPuntoDePedido(resultSet.getInt("puntodepedido"));
                 arrayListProductos.add(traer);
            }     
      
        } catch (SQLException ex) {
            Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally  {
           try {
               this.cerrarStament();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
           try {
               this.cerrarResultSet();
           } catch (SQLException ex) {
               Logger.getLogger(ODetallesCotizaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return arrayListProductos;
    }

    @Override
    public int insertar(AProducto a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(AProducto a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public ArrayList<AProducto> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<AProducto> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> obtenerNombreProducto(int idProducto,OManager traer) {
  
        ArrayList<String> nombre= new ArrayList<>();
     
     
       obtenerUno(idProducto).forEach((AProducto ap) -> {
           if (ap.getIdVademecum() != CONSTANTENOFARMACEUTICO) {
                traer.getVademecum().obtenerUno(ap.getIdVademecum()).forEach((AVademecum av)->{
                 traer.getFormaFarmaceutica().obtenerUno(av.getFormaFarmaceutica()).forEach((AFormaFarmaceutica af)->{
                     traer.getNombreComercial().obtenerUno(av.getNombreComercial()).forEach((ANombreComercial anc)->{
                          nombre.add(anc.getDescripcion());
                     }); 
                  });
          });
             } else {
                        traer.getNoFarmaceutico().obtenerUno(ap.getIdNoFamaceutico()).forEach((ANoFarmaceutico an)->{
                  
                    traer.getNombreComercial().obtenerUno(an.getNombreComercial()).forEach((ANombreComercial anc) -> {
                     nombre.add(anc.getDescripcion());
                        });
                            
                            
                        });
                       }
          });
            
        return nombre;
    
}
}