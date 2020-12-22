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
public class ADetalleOrdenDeCompra {
    private int idDetalleOC,numeroOC,cantidad,idDetallePedidos,	
            idDetalleOferta;
    private long id_producto;
    private double precioUnitario;

    public ADetalleOrdenDeCompra() {
    }

    public int getIdDetalleOC() {
        return idDetalleOC;
    }

    public void setIdDetalleOC(int idDetalleOC) {
        this.idDetalleOC = idDetalleOC;
    }

    public int getNumeroOC() {
        return numeroOC;
    }

    public void setNumeroOC(int numeroOC) {
        this.numeroOC = numeroOC;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

   

  
    public int getIdDetallePedidos() {
        return idDetallePedidos;
    }

    public void setIdDetallePedidos(int idDetallePedidos) {
        this.idDetallePedidos = idDetallePedidos;
    }

    public int getIdDetalleOferta() {
        return idDetalleOferta;
    }

    public void setIdDetalleOferta(int idDetalleOferta) {
        this.idDetalleOferta = idDetalleOferta;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

 
    
    
    
}
