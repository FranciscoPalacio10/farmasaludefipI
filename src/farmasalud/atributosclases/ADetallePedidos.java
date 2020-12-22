/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmasalud.atributosclases;

/**
 *
 * @author Francisco Palacio
 */
public class ADetallePedidos {
    private int idDetalle,cantidad,idProducto,cantidadEnvioInterno,numeroPedidos;
    private String nombreProducto;

    public String getNombreProducto() {
    
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public ADetallePedidos() {
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getNumeroPedidos() {
        return numeroPedidos;
    }

    public void setNumeroPedidos(int numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadEnvioInterno() {
        return cantidadEnvioInterno;
    }

    public void setCantidadEnvioInterno(int cantidadEnvioInterno) {
        this.cantidadEnvioInterno = cantidadEnvioInterno;
    }

 

   
    
    
}
