/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.operaciones;

import farmasalud.atributosclases.ANoFarmaceutico;
import farmasalud.atributosclases.AProveedor;
import farmasalud.conectarBD.ConexionB;
import interfaces.IProveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Palacio
 */
class OProveedor extends ConexionB implements IProveedor {

    private AProveedor traer;
    private final Connection conexion;

    public OProveedor(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public ArrayList<AProveedor> obtenerTodos() {
        ArrayList<AProveedor> arrayListProveedor = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM proveedor");
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AProveedor();
                traer.setDescripcion(resultSet.getString("descripcion"));
                traer.setCondicionesAfip(resultSet.getInt("condicionesafip"));
                traer.setCuit(resultSet.getLong("cuit"));
                traer.setIdProveedor(resultSet.getInt("id_proveedor"));
                traer.setIdTipoProveedor(resultSet.getInt("id_tipoproveedor"));
                traer.setMail(resultSet.getString("email"));
                traer.setNombre(resultSet.getString("nombre"));
                traer.setTelefono(resultSet.getLong("telefono"));
                traer.setCalle(resultSet.getString("calle"));
                traer.setIdProvincia(resultSet.getString("id_provincia"));
                arrayListProveedor.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListProveedor;
    }

    @Override
    public ArrayList<AProveedor> obtenerUno(Integer id) {
        ArrayList<AProveedor> arrayListProveedor = new ArrayList<>();
        try {
            miStatement = conexion.prepareStatement("SELECT * FROM proveedor WHERE id_proveedor=?");
            miStatement.setInt(1, id);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AProveedor();
                traer.setDescripcion(resultSet.getString("descripcion"));
                traer.setCondicionesAfip(resultSet.getInt("condicionesafip"));
               traer.setCuit(resultSet.getLong("cuit"));
                traer.setIdProveedor(resultSet.getInt("id_proveedor"));
                traer.setIdTipoProveedor(resultSet.getInt("id_tipoproveedor"));
                traer.setMail(resultSet.getString("email"));
                traer.setNombre(resultSet.getString("nombre"));
                traer.setTelefono(resultSet.getLong("telefono"));
                traer.setCalle(resultSet.getString("calle"));
                traer.setIdProvincia(resultSet.getString("id_provincia"));
                arrayListProveedor.add(traer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arrayListProveedor;
    }

    @Override
    public int insertar(AProveedor a) {
        int idProveedorInsertado = 0;
        try {
            miStatement = conexion.prepareStatement("INSERT INTO proveedor(nombre,email,descripcion,id_provincia,cuit,"
                    + "	condicionesafip,id_tipoproveedor,telefono,calle) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            miStatement.setString(1, a.getNombre());
            miStatement.setString(2, a.getMail());
            miStatement.setString(3, a.getDescripcion());
            miStatement.setString(4, a.getIdProvincia());
            miStatement.setLong(5, a.getCuit());
            miStatement.setInt(6, a.getCondicionesAfip());
            miStatement.setInt(7, a.getIdTipoProveedor());
            miStatement.setLong(8, a.getTelefono());
            miStatement.setString(9, a.getCalle());
            miStatement.executeUpdate();
            resultSet = miStatement.getGeneratedKeys();
            while (resultSet.next()) {

                idProveedorInsertado = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OCabeceraPedidos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarProcedimiento();
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OCabeceraCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idProveedorInsertado;
    }

    @Override
    public void modificar(AProveedor a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<AProveedor> ejecutarProcedimientoAlmacenado(String procedimiento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ArrayList<AProveedor> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int obtenerIdProveedorXNombre(String nombre) {
        int idProveedor = 0;
        try {
            miStatement = conexion.prepareStatement("SELECT id_proveedor FROM proveedor WHERE nombre=?");
            miStatement.setString(1, nombre);
            resultSet = miStatement.executeQuery();
            while (resultSet.next()) {
                traer = new AProveedor();
                idProveedor = resultSet.getInt("id_proveedor");
            }

        } catch (SQLException ex) {
            Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.cerrarStament();
            } catch (SQLException ex) {
                Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.cerrarResultSet();
            } catch (SQLException ex) {
                Logger.getLogger(OProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idProveedor;
    }

    

}
